package com.example.eshikshauser.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_user")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    @NonNull
    @Column(name = "name")
    public String name;
    @NonNull
    @Column(name = "email")
    public String email;
    @NonNull
    @Column(name = "gender")
    public Integer gender;
    @NonNull
    @Column(name = "password")
    public String password;
}