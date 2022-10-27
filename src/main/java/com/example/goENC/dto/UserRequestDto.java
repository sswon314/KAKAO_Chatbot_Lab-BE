package com.example.goENC.dto;

import com.example.goENC.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private Long userId = null;
    private String nickName = null;
    private String profileImg = null;
    private String eMail = null;
    private LocalDateTime joinDate = null;

    public UserRequestDto(Long userId, String nickName, String profileImg, String eMail, LocalDateTime joinDate) {
        this.userId = userId;
        this.nickName = nickName;
        this.profileImg = profileImg;
        this.eMail = eMail;
        this.joinDate = joinDate;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .nickName(nickName)
                .profileImg(profileImg)
                .eMail(eMail)
                .joinDate(joinDate)
                .build();
    }

}