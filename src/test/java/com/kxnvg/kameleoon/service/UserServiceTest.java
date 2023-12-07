package com.kxnvg.kameleoon.service;

import com.kxnvg.kameleoon.dto.UserDto;
import com.kxnvg.kameleoon.entity.User;
import com.kxnvg.kameleoon.mapper.UserMapperImpl;
import com.kxnvg.kameleoon.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Long USER_ID = 1L;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapperImpl userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserWithoutUserInDB() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.ofNullable(null));
        assertThrows(EntityNotFoundException.class, () -> userService.getUser(USER_ID));
    }

    @Test
    void testGetUser() {
        User user = User.builder().id(USER_ID).build();
        UserDto userDto = UserDto.builder().id(USER_ID).build();

        when(userRepository.findById(USER_ID)).thenReturn(Optional.ofNullable(user));
        UserDto actualUserDto = userService.getUser(USER_ID);
        assertEquals(userDto, actualUserDto);
    }

    @Test
    void testCreateUser() {
        UserDto userDto = UserDto.builder().id(USER_ID).build();
        User user = User.builder().id(USER_ID).build();

        userService.createUser(userDto);
        verify(userRepository).save(user);
    }
}