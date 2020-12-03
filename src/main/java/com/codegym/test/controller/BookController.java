package com.codegym.test.controller;

import com.codegym.test.model.Book;
import com.codegym.test.model.Category;
import com.codegym.test.service.impl.BookServiceImpl;
import com.codegym.test.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/books")
    public ModelAndView listBook() {
        Iterable<Book> books = bookService.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView addBookForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveBook(@ModelAttribute("book")Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message", "New book added successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("book", book);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateBook(@ModelAttribute("book")Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("book",book);
        modelAndView.addObject("message", "Book updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book != null) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("book", book);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/delete")
    public String deleteBook(@ModelAttribute("book")Book book) {
        bookService.remove(book.getBookId());
        return "redirect:books";
    }
}
