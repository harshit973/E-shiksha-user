package com.example.eshikshauser.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_user")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    @Column(name = "name")
    public String name;
    @Column(name = "email")
    public String email;
    @Column(name = "gender")
    public Integer gender;
    @Column(name = "password")
    public String password;
}