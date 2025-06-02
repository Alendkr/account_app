package com.example.account_app.service.receipt;

import com.example.account_app.dto.ReceiptDTO;
import com.example.account_app.model.Receipt;
import com.example.account_app.model.User;
import com.example.account_app.repository.ReceiptRepository;
import com.example.account_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final UserRepository userRepository;

    public ReceiptService(ReceiptRepository receiptRepository, UserRepository userRepository) {
        this.receiptRepository = receiptRepository;
        this.userRepository = userRepository;
    }

    public List<ReceiptDTO> getAllByUserLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Receipt> receipts = receiptRepository.findByUser(user);

        return receipts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ReceiptDTO mapToDTO(Receipt receipt) {
        return new ReceiptDTO(
                receipt.getId(),
                receipt.getDescr(),
                receipt.getAmount(),
                receipt.getReceiptDate(),
                receipt.getCategory() != null ? receipt.getCategory().getId() : null,
                receipt.getCategory() != null ? receipt.getCategory().getName() : null
        );
    }
}
