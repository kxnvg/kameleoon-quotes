package com.kxnvg.kameleoon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDto {

    private Long id;

    private Boolean voteFlag;

    @NotNull(message = "User's id cannot be null")
    private Long userId;

    @NotNull(message = "QuoteId cannot be null")
    private Long quoteId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime votedAt;
}
