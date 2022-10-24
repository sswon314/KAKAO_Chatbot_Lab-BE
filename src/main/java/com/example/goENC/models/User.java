package com.example.goENC.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // SQL에서 auto_increment 의미
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "login_info", length = 100)
    private String loginInfo;

    public User(Long userId) {
        this.userId = userId;
    }

    @Builder
    public User(Long userId, String loginInfo) {
        this.userId = userId;
        this.loginInfo = loginInfo;
    }


}
