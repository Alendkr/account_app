package com.example.account_app.service.receipt;

import com.example.account_app.dto.ReceiptDTO;
import com.example.account_app.mapper.ReceiptMapper;
import com.example.account_app.model.Receipt;
import com.example.account_app.model.User;
import com.example.account_app.repository.ReceiptRepository;
import com.example.account_app.repository.UserRepository;
import com.example.account_app.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final UserRepository userRepository;

    public ReceiptService(ReceiptRepository receiptRepository, UserRepository userRepository) {
        this.receiptRepository = receiptRepository;
        this.userRepository = userRepository;
    }

    public List<ReceiptDTO> getReceiptsForCurrentUser() {
        int currentUserId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return receiptRepository.findByUser(user).stream()
                .map(ReceiptMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReceiptDTO> getReceiptById(int id) {
        return receiptRepository.findById(id)
                .map(ReceiptMapper::toDTO);
    }

    public void createReceipt(Receipt receipt) {
        int currentUserId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        receipt.setUser(user);
        receiptRepository.save(receipt);
    }

    public void deleteReceipt(int id) {
        receiptRepository.deleteById(id);
    }
}

