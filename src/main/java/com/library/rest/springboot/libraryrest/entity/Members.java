package com.library.rest.springboot.libraryrest.entity;




import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "members")
public class Members {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "email")
    private String email;

    @JsonManagedReference //to avoid circular dependency because of bi-directional database mapping
    @OneToMany(mappedBy = "members", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Books> books;


    public Members() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public void addBooks(Books book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        //book.setMembers(this);
    }

    @Override
    public String toString() {
        return firstName + lastName;
    }
}
