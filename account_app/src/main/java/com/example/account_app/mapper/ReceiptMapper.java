package com.example.account_app.mapper;

import com.example.account_app.dto.ReceiptDTO;
import com.example.account_app.model.Category;
import com.example.account_app.model.Receipt;
import com.example.account_app.model.User;

public class ReceiptMapper {

    public static ReceiptDTO toDTO(Receipt receipt) {
        ReceiptDTO dto = new ReceiptDTO();
        dto.setId(receipt.getId());
        dto.setUserId(receipt.getUser().getId());
        dto.setDescr(receipt.getDescr());
        dto.setAmount(receipt.getAmount());
        dto.setReceiptDate(receipt.getReceiptDate());
        if (receipt.getCategory() != null) {
            dto.setCategoryId(receipt.getCategory().getId());
            dto.setCategoryName(receipt.getCategory().getName());
        }
        return dto;
    }

    public static Receipt toEntity(ReceiptDTO dto, User user, Category category) {
        Receipt receipt = new Receipt();
        receipt.setId(dto.getId());
        receipt.setUser(user);
        receipt.setDescr(dto.getDescr());
        receipt.setAmount(dto.getAmount());
        receipt.setReceiptDate(dto.getReceiptDate());
        receipt.setCategory(category);
        return receipt;
    }
}
