package com.example.goENC.dto;

import com.example.goENC.models.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private Long userId; // 유저 ID(PK)
    private String nickName; // 유저 닉네임
    private String profileImg; // 유저 프로필사진(링크)
    private String eMail; // 유저 이메일
    private LocalDateTime joinDate; // 유저 가입일

    public UserResponseDto(User entity) {
        this.userId = entity.getUserId();
        this.nickName = entity.getNickName();
        this.profileImg = entity.getProfileImg();
        this.eMail = entity.getEMail();
        this.joinDate = entity.getJoinDate();
    }

}