package com.example.goENC.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id  // Primary key
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "nick_name", length = 50)
    private String nickName;

    @Column(name = "profile_img", length = 200)
    private String profileImg;

    @Column(name = "email", length = 100)
    private String eMail;

    @Column(name = "join_date")
    private LocalDateTime joinDate;


    public User(Long userId) {
        this.userId = userId;
    }

    @Builder
    public User(Long userId, String nickName, String profileImg, String eMail, LocalDateTime joinDate) {
        this.userId = userId;
        this.nickName = nickName;
        this.profileImg = profileImg;
        this.eMail = eMail;
        this.joinDate = joinDate;
    }


}
