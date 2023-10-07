package com.example.demo.Roles;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
public class Roles {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "customer_sequence"
    )
    private Long id;
    private String name;

    public Roles() {
    }
    public Roles(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Roles(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}