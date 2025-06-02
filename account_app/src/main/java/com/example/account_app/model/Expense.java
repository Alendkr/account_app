package com.example.account_app.model;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    private String descr;

    @NotNull
    private int amount;

    @NotNull
    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;
}
