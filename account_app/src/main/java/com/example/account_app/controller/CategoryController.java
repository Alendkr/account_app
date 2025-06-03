package com.example.account_app.controller;

import com.example.account_app.dto.CategoryDTO;
import com.example.account_app.mapper.CategoryMapper;
import com.example.account_app.model.Category;
import com.example.account_app.service.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Получить список категорий текущего пользователя
    @GetMapping("/me")
    public List<CategoryDTO> getMyCategories() {
        return categoryService.getCurrentUserCategories();
    }


    // Создать новую категорию
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            Category category = CategoryMapper.toEntity(categoryDTO);
            categoryService.createCategory(category);
            return ResponseEntity.ok("Category created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error creating category: " + e.getMessage());
        }
    }

    // Удалить категорию по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error deleting category: " + e.getMessage());
        }
    }
}
