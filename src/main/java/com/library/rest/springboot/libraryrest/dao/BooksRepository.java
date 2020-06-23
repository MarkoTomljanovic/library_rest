package com.library.rest.springboot.libraryrest.dao;

import com.library.rest.springboot.libraryrest.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {
}
