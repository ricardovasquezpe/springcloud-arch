package com.revendedor.userservice.application.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.revendedor.userservice.application.dto.UserDto;
import com.revendedor.userservice.repository.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto fromEntityToDto(User u){
        return DozerBeanMapperBuilder.buildDefault().map(u, UserDto.class);
    }
}
