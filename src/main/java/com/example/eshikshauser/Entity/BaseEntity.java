package com.example.eshikshauser.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    protected Long id;

    @Column
    protected Boolean deleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    protected Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
        this.setDeleted(false);
    }

    @PreUpdate
    protected void onUpdate() {
        this.setUpdatedAt(new Date());
    }

}
