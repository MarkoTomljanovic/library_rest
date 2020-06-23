package com.library.rest.springboot.libraryrest.service;

import com.library.rest.springboot.libraryrest.dao.MembersRepository;
import com.library.rest.springboot.libraryrest.entity.Members;
import com.library.rest.springboot.libraryrest.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MembersServiceImpl implements MembersService {

    private MembersRepository membersRepository;

    @Autowired
    public MembersServiceImpl(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }

    @Override
    public List<Members> findAll() {
        return membersRepository.findAll();
    }

    @Override
    public Members findById(int id) {

        Optional<Members> result = membersRepository.findById(id);

        Members member = null;

        if(result.isPresent()){
            member = result.get();
        }else{
            throw new CustomException("Member with id " + id + " not found");
        }
        return member;
    }

    @Override
    public void save(Members member) {
        membersRepository.save(member);
    }

    @Override
    public void deleteById(int id) {
        membersRepository.deleteById(id);
    }


}
