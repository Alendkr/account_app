package com.example.account_app.mapper;

import com.example.account_app.dto.CategoryDTO;
import com.example.account_app.model.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        if (category == null) return null;
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    public static Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        // ID не устанавливаем — пусть создаётся в БД
        category.setName(dto.getName());
        return category;
    }

    public static List<CategoryDTO> toDTOList(List<Category> categories) {
        if (categories == null) return List.of();
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
