package com.example.user.service.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "micro_users")
public class User {
    @Id
    @Column(name = "id")
    private String userId;
    private String name;
    private String email;
    private String about;
}
