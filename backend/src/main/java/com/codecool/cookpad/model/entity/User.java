package com.codecool.cookpad.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    @OneToOne
    private Role role;
}
