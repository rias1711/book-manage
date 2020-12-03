package com.codegym.test;

import com.codegym.test.service.BookService;
import com.codegym.test.service.CategoryService;
import com.codegym.test.service.impl.BookServiceImpl;
import com.codegym.test.service.impl.CategoryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
