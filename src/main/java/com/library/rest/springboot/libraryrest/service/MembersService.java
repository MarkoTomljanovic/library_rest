package com.library.rest.springboot.libraryrest.service;

import com.library.rest.springboot.libraryrest.entity.Books;
import com.library.rest.springboot.libraryrest.entity.Members;

import java.util.List;

public interface MembersService {
    List<Members> findAll();
    Members findById(int id);
    void save(Members member);
    void deleteById(int id);
}
