package com.example.account_app.controller;

import com.example.account_app.dto.ReceiptDTO;
import com.example.account_app.service.ReceiptService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;
    private static final Logger log = LoggerFactory.getLogger(ReceiptController.class);

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/me")
    public List<ReceiptDTO> getMyReceipts() {
        log.info("Fetching receipts for current user");
        List<ReceiptDTO> receipts = receiptService.getReceiptsForCurrentUser();
        log.info("Found {} receipts", receipts.size());
        return receipts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptDTO> getReceiptById(@PathVariable int id) {
        log.info("Fetching receipt by id: {}", id);
        return receiptService.getReceiptById(id)
                .map(dto -> {
                    log.info("Receipt found: {}", dto);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    log.warn("Receipt not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<String> createReceipt(@RequestBody ReceiptDTO receiptDTO) {
        try {
            log.info("Creating new receipt: {}", receiptDTO);
            receiptService.createReceipt(receiptDTO);
            log.info("Receipt created successfully");
            return ResponseEntity.ok("Receipt created successfully");
        } catch (RuntimeException e) {
            log.error("Error creating receipt: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReceipt(@PathVariable int id) {
        try {
            log.info("Deleting receipt with id: {}", id);
            receiptService.deleteReceipt(id);
            log.info("Receipt deleted successfully");
            return ResponseEntity.ok("Receipt deleted successfully");
        } catch (RuntimeException e) {
            log.error("Error deleting receipt: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
