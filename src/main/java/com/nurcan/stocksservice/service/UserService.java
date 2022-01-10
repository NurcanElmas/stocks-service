package com.nurcan.stocksservice.service;

import com.nurcan.stocksservice.domain.model.User;
import com.nurcan.stocksservice.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user);
    List<UserDto> findAll();
    UserDto findById(long id);
    void delete(long id);
}
