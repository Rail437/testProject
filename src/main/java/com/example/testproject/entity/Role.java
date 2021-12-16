package com.example.testproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Сущность ролей пользователя.
 * @author Yunusov R.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }
}