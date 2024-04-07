package com.todo.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}