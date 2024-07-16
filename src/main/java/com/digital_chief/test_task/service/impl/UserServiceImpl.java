package com.digital_chief.test_task.service.impl;

import com.digital_chief.test_task.dto.UserDto;
import com.digital_chief.test_task.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return List.of();
    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
