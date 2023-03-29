package com.revendedor.userservice.api.controller;

import com.revendedor.userservice.application.dto.UserDto;
import com.revendedor.userservice.service.interf.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> findAll(){
        return new ResponseEntity<List<UserDto>>(usersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") int userId){
        UserDto dto = usersService.findById(userId);
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }
}
