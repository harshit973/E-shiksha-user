package com.example.eshikshauser.Entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_educator")
@EqualsAndHashCode(callSuper = true)
public class Educator extends BaseEntity implements Serializable{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public User user;
    @Column(name = "rating")
    public Integer rating;
}
