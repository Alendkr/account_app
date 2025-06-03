package com.example.account_app.mapper;

import com.example.account_app.dto.ReceiptDTO;
import com.example.account_app.model.Category;
import com.example.account_app.model.Receipt;
import com.example.account_app.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class ReceiptMapper {

    public static ReceiptDTO toDTO(Receipt receipt) {
        if (receipt == null) return null;

        ReceiptDTO dto = new ReceiptDTO();
        dto.setId(receipt.getId());
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
        if (dto == null) return null;

        Receipt receipt = new Receipt();
        // Не устанавливаем ID — пусть сгенерируется в БД

        receipt.setUser(user);
        receipt.setDescr(dto.getDescr());
        receipt.setAmount(dto.getAmount());
        receipt.setReceiptDate(dto.getReceiptDate());
        receipt.setCategory(category);

        return receipt;
    }

    public static List<ReceiptDTO> toDTOList(List<Receipt> receipts) {
        if (receipts == null) return List.of();

        return receipts.stream()
                .map(ReceiptMapper::toDTO)
                .collect(Collectors.toList());
    }
}
