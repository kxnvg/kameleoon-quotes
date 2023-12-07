package com.kxnvg.kameleoon.service;

import com.kxnvg.kameleoon.dto.QuoteDto;
import com.kxnvg.kameleoon.dto.VoteDto;
import com.kxnvg.kameleoon.entity.Quote;
import com.kxnvg.kameleoon.entity.User;
import com.kxnvg.kameleoon.entity.Vote;
import com.kxnvg.kameleoon.mapper.QuoteMapperImpl;
import com.kxnvg.kameleoon.mapper.VoteMapperImpl;
import com.kxnvg.kameleoon.repository.QuoteRepository;
import com.kxnvg.kameleoon.repository.VoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    private static final Long QUOTE_ID = 1L;
    private static final Long USER_ID = 1L;

    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private VoteRepository voteRepository;

    @Spy
    private QuoteMapperImpl quoteMapper;

    @Spy
    private VoteMapperImpl voteMapper;

    @InjectMocks
    private QuoteService quoteService;

    private User user;
    private Quote quote;
    private QuoteDto quoteDto;

    @BeforeEach
    void dataInit() {
        user = User.builder().id(USER_ID).build();
        quote = Quote.builder().id(QUOTE_ID).user(user).build();
        quoteDto = QuoteDto.builder().id(QUOTE_ID).userId(USER_ID).build();
    }

    @Test
    void testGetQuoteWithoutQuote() {
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(null));
        assertThrows(EntityNotFoundException.class, () -> quoteService.getQuote(QUOTE_ID));
    }

    @Test
    void testGetQuote() {
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(quote));
        QuoteDto actualQuoteDto = quoteService.getQuote(QUOTE_ID);
        assertEquals(quoteDto, actualQuoteDto);
    }

    @Test
    void testGetRandomQuoteWithoutQuote() {
        when(quoteRepository.getRandomQuote()).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> quoteService.getRandomQuote());
    }

    @Test
    void testGetRandomQuote() {
        when(quoteRepository.getRandomQuote()).thenReturn(quote);
        QuoteDto actualQuoteDto = quoteService.getRandomQuote();
        assertEquals(quoteDto, actualQuoteDto);
    }

    @Test
    void testCreateQuote() {
        quoteService.createQuote(quoteDto);
        quote.setVotes(0L);
        verify(quoteRepository).save(quote);
    }

    @Test
    void testUpdateQuoteWithoutQuote() {
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(null));
        assertThrows(EntityNotFoundException.class, () -> quoteService.updateQuote(quoteDto));
    }

    @Test
    void testUpdateQuote() {
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(quote));
        quoteDto.setContent("test");

        QuoteDto actualQuoteDto = quoteService.updateQuote(quoteDto);
        assertEquals(quoteDto, actualQuoteDto);
    }

    @Test
    void testDeleteQuoteWithFalseResult() {
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(null));
        boolean actualResult = quoteService.deleteQuote(QUOTE_ID);
        assertEquals(false, actualResult);
    }

    @Test
    void testDeleteQuoteWithTrueResult() {
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(quote));

        boolean actualResult = quoteService.deleteQuote(QUOTE_ID);
        verify(quoteRepository).deleteById(QUOTE_ID);
        assertEquals(true, actualResult);
    }

    @Test
    void testUpvoteWithoutQuote() {
        VoteDto voteDto = VoteDto.builder().quoteId(QUOTE_ID).build();
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(null));
        assertThrows(EntityNotFoundException.class, () -> quoteService.upvote(voteDto));
    }

    @Test
    void testUpvote() {
        quote.setVotes(0L);
        VoteDto voteDto = VoteDto.builder().quoteId(QUOTE_ID).build();
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(quote));

        quoteService.upvote(voteDto);
        assertEquals(1L, quote.getVotes());
    }

    @Test
    void testDownvoteWithoutQuote() {
        VoteDto voteDto = VoteDto.builder().quoteId(QUOTE_ID).build();
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(null));
        assertThrows(EntityNotFoundException.class, () -> quoteService.downvote(voteDto));
    }

    @Test
    void testDwonvote() {
        quote.setVotes(1L);
        VoteDto voteDto = VoteDto.builder().quoteId(QUOTE_ID).build();
        when(quoteRepository.findById(QUOTE_ID)).thenReturn(Optional.ofNullable(quote));

        quoteService.downvote(voteDto);
        assertEquals(0L, quote.getVotes());
    }

    @Test
    void testGetTopTenQuotes() {
        when(quoteRepository.getTopTenQuotes()).thenReturn(getQuoteList());
        List<QuoteDto> actualList = quoteService.getTopTenQuotes();
        assertEquals(getQuoteDtoList(), actualList);
    }

    @Test
    void testGetWorseTenQuotes() {
        when(quoteRepository.getWorseTenQuotes()).thenReturn(getQuoteList());
        List<QuoteDto> actualList = quoteService.getWorseTenQuotes();
        assertEquals(getQuoteDtoList(), actualList);
    }

    @Test
    void testGetGraphEvolution() {
        when(voteRepository.findAllQuoteVotes(QUOTE_ID)).thenReturn(getVoteList());
        List<VoteDto> actualList = quoteService.getGraphEvolution(QUOTE_ID);
        assertEquals(getVoteDtoList(), actualList);
    }

    private List<QuoteDto> getQuoteDtoList() {
        QuoteDto dto1 = QuoteDto.builder()
                .id(1L)
                .userId(1L)
                .build();
        QuoteDto dto2 = QuoteDto.builder()
                .id(2L)
                .userId(2L)
                .build();
        QuoteDto dto3 = QuoteDto.builder()
                .id(3L)
                .userId(3L)
                .build();
        return List.of(dto1, dto2, dto3);
    }

    private List<Quote> getQuoteList() {
        Quote quote1 = Quote.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .build();
        Quote quote2 = Quote.builder()
                .id(2L)
                .user(User.builder().id(2L).build())
                .build();
        Quote quote3 = Quote.builder()
                .id(3L)
                .user(User.builder().id(3L).build())
                .build();
        return List.of(quote1, quote2, quote3);
    }

    private List<Vote> getVoteList() {
        Vote vote1 = Vote.builder()
                .user(user)
                .quote(quote)
                .build();
        Vote vote2 = Vote.builder()
                .user(user)
                .quote(quote)
                .build();
        Vote vote3 = Vote.builder()
                .user(user)
                .quote(quote)
                .build();
        return List.of(vote1, vote2, vote3);
    }

    private List<VoteDto> getVoteDtoList() {
        VoteDto dto1 = VoteDto.builder()
                .userId(USER_ID)
                .quoteId(QUOTE_ID)
                .build();
        VoteDto dto2 = VoteDto.builder()
                .userId(USER_ID)
                .quoteId(QUOTE_ID)
                .build();
        VoteDto dto3 = VoteDto.builder()
                .userId(USER_ID)
                .quoteId(QUOTE_ID)
                .build();
        return List.of(dto1, dto2, dto3);
    }
}