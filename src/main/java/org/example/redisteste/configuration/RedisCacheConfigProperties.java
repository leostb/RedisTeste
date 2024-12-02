package org.example.redisteste.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "redis.cache")
public class RedisCacheConfigProperties {

    private Map<String, String> ttl;

    public Map<String, String> getTtl() {
        return ttl;
    }

    public void setTtl(Map<String, String> ttl) {
        this.ttl = ttl;
    }

    @PostConstruct
    public void init() {
        // Garantir que as propriedades foram carregadas corretamente
        System.out.println("Loaded TTL Map: " + ttl);
    }
}
