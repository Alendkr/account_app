package com.example.account_app.dto;

import lombok.Data;

@Data
public class CategoryDTO {

    private Integer id;
    private String name;

    public CategoryDTO() {}

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
