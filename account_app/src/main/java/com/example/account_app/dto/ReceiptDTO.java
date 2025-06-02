package com.example.account_app.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReceiptDTO {
    private Integer id;
    private Integer userId;
    private String descr;
    private int amount;
    private LocalDate receiptDate;
    private Integer categoryId;
    private String categoryName;
}
