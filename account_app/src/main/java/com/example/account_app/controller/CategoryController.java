package com.example.account_app.controller;

import com.example.account_app.dto.CategoryDTO;
import com.example.account_app.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/me")
    public List<CategoryDTO> getMyCategories() {
        log.info("Fetching categories for current user");
        return categoryService.getCurrentUserCategories();
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO dto) {
        try {
            log.info("Creating new category: {}", dto.getName());
            categoryService.createCategory(dto);
            return ResponseEntity.ok("Category created successfully");
        } catch (RuntimeException e) {
            log.error("Error creating category: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error creating category: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        try {
            log.info("Deleting category with id: {}", id);
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (RuntimeException e) {
            log.error("Error deleting category: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error deleting category: " + e.getMessage());
        }
    }
}
