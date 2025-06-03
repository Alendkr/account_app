package com.example.account_app.controller;

import com.example.account_app.dto.ReceiptDTO;
import com.example.account_app.model.Receipt;
import com.example.account_app.service.receipt.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/me")
    public List<ReceiptDTO> getMyReceipts() {
        return receiptService.getReceiptsForCurrentUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptDTO> getReceiptById(@PathVariable int id) {
        return receiptService.getReceiptById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createReceipt(@RequestBody Receipt receipt) {
        receiptService.createReceipt(receipt);
        return ResponseEntity.ok("Receipt created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReceipt(@PathVariable int id) {
        receiptService.deleteReceipt(id);
        return ResponseEntity.ok("Receipt deleted successfully");
    }
}
