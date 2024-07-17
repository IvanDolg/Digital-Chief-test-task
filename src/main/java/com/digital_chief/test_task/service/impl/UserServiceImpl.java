package com.digital_chief.test_task.service.impl;

import com.digital_chief.test_task.dto.UserDto;
import com.digital_chief.test_task.entity.Role;
import com.digital_chief.test_task.entity.User;
import com.digital_chief.test_task.exception.RecurseNotFoundException;
import com.digital_chief.test_task.mapper.AutoUserMapper;
import com.digital_chief.test_task.repository.UserRepository;
import com.digital_chief.test_task.security.UserPrincipal;
import com.digital_chief.test_task.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());

        if (optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User already exists");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.getRoles().add(Role.USER);

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("User", "id", String.valueOf(id))
        );

        user.setUsername(userDto.getUsername());
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserPrincipal.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
