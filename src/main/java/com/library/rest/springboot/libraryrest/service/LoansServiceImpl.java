package com.library.rest.springboot.libraryrest.service;

import com.library.rest.springboot.libraryrest.dao.BooksRepository;
import com.library.rest.springboot.libraryrest.dao.MembersRepository;
import com.library.rest.springboot.libraryrest.entity.Books;
import com.library.rest.springboot.libraryrest.entity.Members;
import com.library.rest.springboot.libraryrest.exceptions.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class LoansServiceImpl  implements LoansService {

    private BooksRepository booksRepository;
    private MembersRepository membersRepository;

    @Autowired
    public LoansServiceImpl(BooksRepository booksRepository, MembersRepository membersRepository) {
        this.booksRepository = booksRepository;
        this.membersRepository = membersRepository;
    }

    @Override
    public Members loanBook(int memberId, int bookId) {
        Optional<Members> resultM = membersRepository.findById(memberId);
        Members member = null;
        if(resultM.isPresent()){
            member = resultM.get();
        }else{
            throw new CustomException("Member with id " + memberId + " not found");
        }

        Optional<Books> resultB = booksRepository.findById(bookId);
        Books book = null;
        if(resultB.isPresent()){
            book = resultB.get();
        }else{
            throw new CustomException("Book with id " + bookId + " not found");
        }
        Members memberLoan = book.getMembers();

        if(memberLoan == null){
            member.addBooks(book);
            membersRepository.save(member);
            return member;
        }else{
            throw new CustomException("Book with id " + bookId + " is already loaned");
        }
    }

    @Override
    public Books returnBook(int bookId) {
        Optional<Books> result = booksRepository.findById(bookId);
        Books book = null;

        if(result.isPresent()){
            book = result.get();
        }else{
            throw new CustomException("Book with id " + bookId + " not found");
        }
        Members member = book.getMembers();

        if(member == null){
            throw new CustomException("Book with id " + bookId + " is not on loan");
        }else{
            book.setMembers(null);
            booksRepository.save(book);
            return book;
        }
    }
}
