package com.example.luck.service;

import com.example.luck.dto.UserDto;
import com.example.luck.model.User;

import java.util.List;

public interface UserService {
    User save(UserDto userDto);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}
