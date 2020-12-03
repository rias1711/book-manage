package com.codegym.test.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotEmpty
    private String bookName;

    @NotEmpty
    private String author;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
