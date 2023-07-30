package com.example.eshikshauser.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_educator")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Educator extends BaseEntity implements Serializable {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "experience")
    private Integer experience;
    @Column(name = "rating")
    private Integer rating;

    @PrePersist
    public void onCreate() {
        super.onCreate();
        if (Objects.isNull(this.experience))
            this.experience = 0;
        if (Objects.isNull(this.rating))
            this.rating = 0;
    }

    @PreUpdate
    public void onUpdate() {
        super.onUpdate();
        if (Objects.isNull(this.experience))
            this.experience = 0;
        if (Objects.isNull(this.rating))
            this.rating = 0;
    }

}
