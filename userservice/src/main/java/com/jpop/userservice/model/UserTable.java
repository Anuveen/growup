package com.jpop.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user_table")
@Setter
@Getter
public class UserTable {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public UserTable(){

    }
}
