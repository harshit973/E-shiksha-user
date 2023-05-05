package com.example.eshikshauser.Entity;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private Boolean deleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedAt;

    @PrePersist
    public void onCreate() {
        this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
        this.setDeleted(false);
    }

    @PreUpdate
    public void onUpdate() {
        this.setUpdatedAt(new Date());
    }

}
