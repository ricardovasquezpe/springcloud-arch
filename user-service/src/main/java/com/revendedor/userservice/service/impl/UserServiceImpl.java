package com.revendedor.userservice.service.impl;

import com.revendedor.userservice.application.dto.UserDto;
import com.revendedor.userservice.application.mapper.UserMapper;
import com.revendedor.userservice.repository.UserRepository;
import com.revendedor.userservice.service.interf.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::fromEntityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(int id) {
        return userRepository.findById(id).map(userMapper::fromEntityToDto).orElse(null);
    }
}
