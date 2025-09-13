package com.auction.gateway.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class FallbackController {
    @RequestMapping("/fallback/auction")
    public ResponseEntity<Map<String,Object>> auc() {
        return ResponseEntity.status(503).body(Map.of(
                "service","auction",
                "message","temporarily unavailable",
                "fallback", true
        ));
    }
    @RequestMapping("/fallback/bids")
    public ResponseEntity<Map<String,Object>> bid() {
        return ResponseEntity.status(503).body(Map.of(
                "service","bid",
                "message","temporarily unavailable",
                "fallback", true
        ));
    }
}
