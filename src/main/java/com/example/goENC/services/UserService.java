package com.example.goENC.services;

import com.example.goENC.dto.UserRequestDto;
import com.example.goENC.models.User;
import com.example.goENC.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public Long createUser(UserRequestDto requestDto) {

        User userId = userRepository.save(requestDto.toEntity());

        return userId.getUserId();
    }
}
