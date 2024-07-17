package com.digital_chief.test_task.service;

import com.digital_chief.test_task.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  extends UserDetailsService {
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto, Long id);
    List<UserDto> findAll();
    UserDto findById(Long id);
    void deleteById(Long id);
}
