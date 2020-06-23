package com.library.rest.springboot.libraryrest.service;

import com.library.rest.springboot.libraryrest.dao.BooksRepository;
import com.library.rest.springboot.libraryrest.entity.Books;
import com.library.rest.springboot.libraryrest.entity.Members;
import com.library.rest.springboot.libraryrest.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    private BooksRepository booksRepository;

    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public List<Books> findAll() {
        List<Books> books = booksRepository.findAll();
        return booksRepository.findAll();
    }

    @Override
    public Books findById(int id) {
        Optional<Books> result = booksRepository.findById(id);

        Books book = null;

        if(result.isPresent()){
            book = result.get();
        }else{
            throw new CustomException("Book with id " + id + " not found");
        }
        return book;
    }

    @Override
    public void save(Books book) {
        booksRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }
}
