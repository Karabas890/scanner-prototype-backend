package com.example.scannerprototypebackend.controller;

import com.example.scannerprototypebackend.service.MockDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    private final MockDataService service;

    public DataController(MockDataService service) {
        this.service = service;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<?> getData(@PathVariable String id) {
        Object result = service.getById(id);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
