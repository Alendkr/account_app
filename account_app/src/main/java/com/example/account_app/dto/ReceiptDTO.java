package com.example.account_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private Integer id;
    private String descr;
    private int amount;
    private LocalDate receiptDate;
    private Integer categoryId;
    private String categoryName;
}
