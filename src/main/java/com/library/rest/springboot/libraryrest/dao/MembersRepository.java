package com.library.rest.springboot.libraryrest.dao;

import com.library.rest.springboot.libraryrest.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Integer> {
}
