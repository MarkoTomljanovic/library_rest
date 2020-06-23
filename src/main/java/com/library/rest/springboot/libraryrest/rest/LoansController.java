package com.library.rest.springboot.libraryrest.rest;

import com.library.rest.springboot.libraryrest.entity.Books;
import com.library.rest.springboot.libraryrest.entity.Members;
import com.library.rest.springboot.libraryrest.service.LoansService;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoansController {

    private LoansService loansService;

    @Autowired
    public LoansController(LoansService loansService) {
        this.loansService = loansService;
    }

    @PutMapping("/loans/{memberId}/{bookId}")
    public Members loanBook(@PathVariable int memberId, @PathVariable int bookId){
        return loansService.loanBook(memberId, bookId);
    }

    @PutMapping("/loans/{bookId}")
    public Books returnBook(@PathVariable int bookId){
        return loansService.returnBook(bookId);
    }

}
