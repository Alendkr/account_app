package com.example.account_app.service;

import com.example.account_app.dto.CategoryDTO;
import com.example.account_app.mapper.CategoryMapper;
import com.example.account_app.model.Category;
import com.example.account_app.model.User;
import com.example.account_app.repository.CategoryRepository;
import com.example.account_app.repository.UserRepository;
import com.example.account_app.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<CategoryDTO> getCurrentUserCategories() {
        int currentUserId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return CategoryMapper.toDTOList(categoryRepository.findByUser(user));
    }

    public void createCategory(CategoryDTO dto) {
        int userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = CategoryMapper.toEntity(dto);
        category.setUser(user);

        categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        int userId = SecurityUtils.getCurrentUserId();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (category.getUser().getId() != userId) {
            throw new RuntimeException("Not authorized to delete this category");
        }

        categoryRepository.deleteById(id);
    }

    public Optional<CategoryDTO> getCategoryById(int id) {
        return categoryRepository.findById(id).map(CategoryMapper::toDTO);
    }
}
