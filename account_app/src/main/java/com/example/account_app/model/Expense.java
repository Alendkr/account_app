package com.example.account_app.model;

import io.ebean.Model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "Expenses")
@Data  // генерирует геттеры, сеттеры, toString, equals, hashCode
public class Expense extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    private String descr;
    private int amount;
    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;
}
