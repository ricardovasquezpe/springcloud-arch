package com.revendedor.userservice.service.interf;

import com.revendedor.userservice.application.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> findAll();
    UserDto findById(int id);
}
