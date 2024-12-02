//package org.example.redisteste.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.CacheKeyPrefix;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
//
//@Configuration
//@EnableCaching
//public class RedisConfig {
//
//    @Value("${spring.application.name}")
//    private String applicationName;
//
//
////    @Bean
////    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
////        Logger.getLogger("RedisConfig").info("Creating custom cache configuration");
////
////        // Usando streams para transformar o mapa de TTLs em configurações de cache
////        Map<String, RedisCacheConfiguration> cacheNamesConfigurationMap = ttls.getTtl().entrySet().stream()
////                .collect(Collectors.toMap(
////                        Map.Entry::getKey,  // Chave do cache (nome do cache)
////                        entry -> RedisCacheConfiguration.defaultCacheConfig()
////                                .entryTtl(Duration.ofSeconds(entry.getValue()))  // TTL customizado
////                ));
////
////
////
////        return new RedisCacheManager(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory),
////                //set default expiration for all other caches to 300 seconds (optional)
////                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(300)),
////                cacheNamesConfigurationMap);
////    }
//
//    @Bean
//    public RedisCacheManager cacheManager(final RedisConnectionFactory connectionFactory) {
//        final RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(defaultCacheConfig()
//                        .computePrefixWith(CacheKeyPrefix.prefixed(applicationName))
//                        .entryTtl(Duration.ofMinutes(1))
//                )
//                .build();
//        return cacheManager;
//    }
//
//}
//
//
