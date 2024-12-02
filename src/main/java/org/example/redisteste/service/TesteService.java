package org.example.redisteste.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TesteService {

    @Cacheable("teste")
    public String execute(String id) {
        Logger.getGlobal().info("Passou pelo execute");
        return "Teste";
    }

    @Cacheable("order")
    public String orders(String id) {
        Logger.getGlobal().info("Passou pelo orders");
        return "Order";
    }
}
