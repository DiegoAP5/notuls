package com.example.notuls.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private String password;

    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Collection>collections;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Note>notes;
}
