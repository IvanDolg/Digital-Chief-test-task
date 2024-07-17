package com.digital_chief.test_task.controller;

import com.digital_chief.test_task.dto.LoginUserDto;
import com.digital_chief.test_task.dto.UserDto;
import com.digital_chief.test_task.entity.Role;
import com.digital_chief.test_task.security.JWTTokenProvider;
import com.digital_chief.test_task.security.UserPrincipal;
import com.digital_chief.test_task.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(
        name = "User Controller",
        description = "User controller exposes rest apis"
)
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;

    @Operation(
            summary = "Create new user",
            description = "Used to save new user to a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "User created"
            )
    )
    @PostMapping("/registration")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.save(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Create new jwt token",
            description = "Used to generate new jwt token"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Token generated"
            )
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto dto) {
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(dto.getUsername());

        if (passwordEncoder.matches(dto.getPassword(), userPrincipal.getPassword())) {
            Set<Role> authorities = (Set<Role>) userPrincipal.getAuthorities();
            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername(), userPrincipal.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(
            summary = "Update user by id",
            description = "Used to update user from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "User found"
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              @PathVariable Long id) {
        UserDto updatedUser = userService.update(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all users",
            description = "Used to retrieve all users from db"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Users found"
            )
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Get user by id",
            description = "Used to retrieve user from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "User found"
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete user by id",
            description = "Used to delete user from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "User deleted"
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}