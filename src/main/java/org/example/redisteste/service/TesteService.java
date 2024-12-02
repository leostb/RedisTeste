package org.example.redisteste.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TesteService {

    @Cacheable(value = "testes",key = "#id", cacheResolver = "customCacheResolver")
    public String execute(String id) {
        Logger.getGlobal().info("Passou pelo execute");
        return "Teste";
    }

    @Cacheable(value = "orders",key = "#id", cacheResolver = "customCacheResolver")
    public String orders(String id) {
        Logger.getGlobal().info("Passou pelo orders");
        return "Order";
    }
}
