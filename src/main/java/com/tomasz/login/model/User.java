package com.tomasz.login.model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@SequenceGenerator(name = "user_seq", initialValue = 3, allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq")
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_role")
    private Role role;

    public String getRoleName() {
        return role.getRole();
    }
}
