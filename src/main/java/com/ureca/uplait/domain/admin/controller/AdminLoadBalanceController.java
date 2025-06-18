package com.ureca.uplait.domain.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoadBalanceController {
    @GetMapping("/health")
    public ResponseEntity<String> checkConnection() {
        return ResponseEntity.ok("응답이 반환되었습니다.");
    }
}
