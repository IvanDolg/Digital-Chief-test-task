package com.digital_chief.test_task.service.impl;

import com.digital_chief.test_task.dto.UserDto;
import com.digital_chief.test_task.entity.User;
import com.digital_chief.test_task.exception.RecurseNotFoundException;
import com.digital_chief.test_task.exception.UserAlreadyExistsException;
import com.digital_chief.test_task.mapper.AutoUserMapper;
import com.digital_chief.test_task.repository.UserRepository;
import com.digital_chief.test_task.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto save(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail()); // <Use>

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        user = userRepository.save(user);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("User", "id", String.valueOf(id))
        );

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        User updateUser = userRepository.save(user);
        return AutoUserMapper.MAPPER.mapToUserDto(updateUser);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("User", "id", String.valueOf(id))
        );

        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("User", "id", String.valueOf(id))
        );

        userRepository.delete(user);
    }
}
