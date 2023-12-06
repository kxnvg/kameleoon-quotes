package com.kxnvg.kameleoon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @NotBlank(message = "User's name cannot be empty")
    @Size(max = 64, message = "User's name cannot be more than 64 symbols")
    private String name;

    @NotBlank(message = "Password cannot be empty")
    @Size(max = 128, message = "Password cannot be more than 128 symbols")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 64, message = "Email cannot be more than 64 symbols")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
