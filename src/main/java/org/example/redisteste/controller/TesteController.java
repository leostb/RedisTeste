package org.example.redisteste.controller;

import org.example.redisteste.service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TesteController {

    @Autowired
    private TesteService testeService;

    @GetMapping("/testes/{id}")
    public ResponseEntity<String> teste(@PathVariable String id){
        String execute = testeService.execute(id);
        return ResponseEntity.ok(execute);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<String> order(@PathVariable String id){
        String execute = testeService.orders(id);
        return ResponseEntity.ok(execute);
    }
}
