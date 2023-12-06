package com.kxnvg.kameleoon.controller;

import com.kxnvg.kameleoon.dto.UserDto;
import com.kxnvg.kameleoon.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long userId) {
        log.info("Received request to get user with id={}", userId);
        return userService.getUser(userId);
    }

    @PostMapping
    public void createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Received request to create user with email={}", userDto.getEmail());
        userService.createUser(userDto);
    }
}
