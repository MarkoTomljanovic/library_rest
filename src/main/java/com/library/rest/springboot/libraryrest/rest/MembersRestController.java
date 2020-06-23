package com.library.rest.springboot.libraryrest.rest;

import com.library.rest.springboot.libraryrest.entity.Members;
import com.library.rest.springboot.libraryrest.exceptions.CustomException;
import com.library.rest.springboot.libraryrest.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MembersRestController {

    private MembersService membersService;

    @Autowired
    public MembersRestController(MembersService membersService) {
        this.membersService = membersService;
    }

    @GetMapping("/members")
    public List<Members> findAll(){
        return membersService.findAll();
    }

    @GetMapping("/members/{memberId}")
    public Members findById (@PathVariable int memberId){
        Members member = membersService.findById(memberId);
        if(member == null){
            throw new CustomException("Member with id " + memberId + " not found");
        }
        return member;
    }

    @PostMapping("/members")
    public Members addMember(@RequestBody Members member){
        member.setId(0);
        membersService.save(member);
        return member;
    }

    @PutMapping("/members")
    public Members updateMember(@RequestBody Members member){
        membersService.save(member);
        return member;
    }

    @DeleteMapping("/members/{memberId}")
    public String deleteMember (@PathVariable int memberId){
        Members member = membersService.findById(memberId);
        if(member == null){
            throw new CustomException("Member with id " + memberId + " not found");
        }
        if(!member.getBooks().isEmpty()){
            throw new CustomException("Member with id " + memberId + " not can't be deleted because has books on loan");
        }
        membersService.deleteById(memberId);
        return "Deleted id - " + memberId;
    }

}
