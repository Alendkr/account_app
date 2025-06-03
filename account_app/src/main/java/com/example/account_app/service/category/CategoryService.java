package com.example.account_app.service.category;

import com.example.account_app.dto.CategoryDTO;
import com.example.account_app.mapper.CategoryMapper;
import com.example.account_app.model.Category;
import com.example.account_app.model.User;
import com.example.account_app.repository.CategoryRepository;
import com.example.account_app.repository.UserRepository;
import com.example.account_app.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void createCategory(Category category) {
        int currentUserId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        category.setUser(user);

        categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        // Проверяем, что категория принадлежит текущему пользователю (опционально)
        int currentUserId = SecurityUtils.getCurrentUserId();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (category.getUser().getId() != currentUserId) {
            throw new RuntimeException("Not authorized to delete this category");
        }

        categoryRepository.deleteById(id);
    }
}
