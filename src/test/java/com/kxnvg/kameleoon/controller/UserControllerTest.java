package com.kxnvg.kameleoon.controller;

import com.kxnvg.kameleoon.dto.UserDto;
import com.kxnvg.kameleoon.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private static final Long USER_ID = 1L;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetUser() {
        userController.getUser(USER_ID);
        Mockito.verify(userService).getUser(USER_ID);
    }

    @Test
    void testCreateUser() {
        UserDto userDto = UserDto.builder().id(USER_ID).build();
        userController.createUser(userDto);
        Mockito.verify(userService).createUser(userDto);
    }
}