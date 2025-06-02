package com.example.account_app.model;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt extends Model {

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
    @Column(name = "receipt_date")
    private LocalDate receiptDate;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;
}
