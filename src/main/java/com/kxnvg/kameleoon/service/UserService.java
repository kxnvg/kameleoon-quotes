package com.kxnvg.kameleoon.service;

import com.kxnvg.kameleoon.dto.UserDto;
import com.kxnvg.kameleoon.entity.User;
import com.kxnvg.kameleoon.mapper.UserMapper;
import com.kxnvg.kameleoon.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDto getUser(Long userId) {
        Optional<User> maybeUser = userRepository.findById(userId);
        User user = maybeUser
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id=%s is not found", userId)));
        log.info("Entity user with id={} was taken from DB successfully", user.getId());
        return userMapper.toDto(user);
    }

    @Transactional
    public void createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        log.info("New user with email={} was saved in DB successfully", userDto.getEmail());
    }
}
