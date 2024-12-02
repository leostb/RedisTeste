package org.example.redisteste.configuration;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import org.example.redisteste.CustomCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class CustomCacheResolver implements CacheResolver {

	private static final int DEFAULT_TTL = 30;
	@Autowired
	private final CacheManager cacheManager;
	
	private Logger log = Logger.getLogger("CustomCacheResolver");

	public CustomCacheResolver(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	private Map<String, String> mapNameConfig = new HashMap<String, String>();

	@Autowired
	RedisCacheConfigProperties cacheConfigProperties;

	@PostConstruct
	public void init() {
		if(cacheConfigProperties != null && cacheConfigProperties.getTtl() != null){
			mapNameConfig = cacheConfigProperties.getTtl();
		}
	}

	@Override
	public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
		log.warning("CustomCacheResolver resolveCaches");
		if (context != null && context.getOperation() != null && (context.getOperation().getCacheNames() != null
				&& !context.getOperation().getCacheNames().isEmpty())) {
			Collection<CustomCache> caches = new HashSet<>();

			Set<String> cacheNames = context.getOperation().getCacheNames().stream().collect(Collectors.toSet());

			cacheNames.forEach(cacheName -> {

				Cache cache = cacheManager.getCache(cacheName);
				CustomCache customCache = cacheWithCustomTTL(cacheName, cache);
				caches.add(customCache);
			});
			return caches;

		}
		// this should not reach here
		log.warning("CustomCacheResolver context is empty");
		return Collections.emptyList();
	}

	private CustomCache cacheWithCustomTTL(String cacheName, Cache cache) {
		int ttl = namePartOfPrefix(cacheName);

		RedisCache redisCache = (RedisCache) cache;

		RedisCacheWriter cacheWriter = redisCache.getNativeCache();
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(ttl));
		CustomCache customCache = new CustomCache(cacheName, cacheWriter, cacheConfig);
		return customCache;
	}

	private int namePartOfPrefix(String name) {

		for (Map.Entry<String, String> entry : mapNameConfig.entrySet()) {
			if (name.startsWith(entry.getKey())) {
				return Integer.parseInt(entry.getValue());
			}
		}
		return DEFAULT_TTL;
	}
}