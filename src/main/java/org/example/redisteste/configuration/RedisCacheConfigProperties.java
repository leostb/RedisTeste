package org.example.redisteste.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "redis.cache")
public class RedisCacheConfigProperties {

    private Map<String, Long> ttl;

    public Map<String, Long> getTtl() {
        return ttl;
    }

    public void setTtl(Map<String, Long> ttl) {
        this.ttl = ttl;
    }

    @PostConstruct
    public void init() {
        // Garantir que as propriedades foram carregadas corretamente
        System.out.println("Loaded TTL Map: " + ttl);
    }
}
