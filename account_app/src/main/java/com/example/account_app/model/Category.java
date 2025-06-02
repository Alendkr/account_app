package com.example.account_app.model;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Override
    public String toString() {
        return name;
    }
}
