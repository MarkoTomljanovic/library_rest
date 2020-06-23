package com.library.rest.springboot.libraryrest.service;

import com.library.rest.springboot.libraryrest.entity.Books;
import com.library.rest.springboot.libraryrest.entity.Members;

public interface LoansService {
    Members loanBook(int memberId, int bookId);
    Books returnBook(int bookId);
}
