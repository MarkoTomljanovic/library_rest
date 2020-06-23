package com.library.rest.springboot.libraryrest.service;

import com.library.rest.springboot.libraryrest.entity.Books;
import com.library.rest.springboot.libraryrest.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksService {
    List<Books> findAll();
    Books findById(int id);
    void save(Books book);
    void deleteById(int id);
}
