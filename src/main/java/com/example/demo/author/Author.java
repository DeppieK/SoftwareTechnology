package com.example.demo.author;

import com.example.demo.User.UserEntity;
import jakarta.persistence.*;

@Entity

@Table
public class Author {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    //Constructors
    public Author() {
    }

    //Getters and Setters
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //ToString
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                '}';
    }
}

