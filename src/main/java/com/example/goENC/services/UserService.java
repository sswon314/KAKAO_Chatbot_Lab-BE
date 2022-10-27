package com.example.goENC.services;

import com.example.goENC.dto.UserResponseDto;
import com.example.goENC.dto.UserRequestDto;
import com.example.goENC.models.User;
import com.example.goENC.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
