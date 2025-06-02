package com.example.account_app.util;


import com.example.account_app.model.User;
import com.example.account_app.service.user.CustomUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetailsService) {
            return ((CustomUserDetailsService) principal).getId(); // 💥 предполагаем, что ты добавил getId()
        } else {
            throw new RuntimeException("Principal is not an instance of CustomUserDetails");
        }
    }
}
