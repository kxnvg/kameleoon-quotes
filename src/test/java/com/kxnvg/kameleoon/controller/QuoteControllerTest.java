package com.kxnvg.kameleoon.controller;

import com.kxnvg.kameleoon.dto.QuoteDto;
import com.kxnvg.kameleoon.dto.VoteDto;
import com.kxnvg.kameleoon.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuoteControllerTest {

    private static final Long QUOTE_ID = 1L;

    @Mock
    private QuoteService quoteService;

    @InjectMocks
    private QuoteController quoteController;

    @Test
    void testGetQuote() {
        quoteController.getQuote(QUOTE_ID);
        verify(quoteService).getQuote(QUOTE_ID);
    }

    @Test
    void testGetRandomQuote() {
        quoteController.getRandomQuote();
        verify(quoteService).getRandomQuote();
    }

    @Test
    void testCreateQuote() {
        QuoteDto quoteDto = QuoteDto.builder().id(QUOTE_ID).build();
        quoteController.createQuote(quoteDto);
        verify(quoteService).createQuote(quoteDto);
    }

    @Test
    void testUpdateQuote() {
        QuoteDto quoteDto = QuoteDto.builder().id(QUOTE_ID).build();
        quoteController.updateQuote(quoteDto);
        verify(quoteService).updateQuote(quoteDto);
    }

    @Test
    void testDeleteQuote() {
        quoteController.deleteQuote(QUOTE_ID);
        verify(quoteService).deleteQuote(QUOTE_ID);
    }

    @Test
    void testUpvote() {
        VoteDto voteDto = VoteDto.builder().build();
        quoteController.upvote(voteDto);
        verify(quoteService).upvote(voteDto);
    }

    @Test
    void testDownvote() {
        VoteDto voteDto = VoteDto.builder().build();
        quoteController.downvote(voteDto);
        verify(quoteService).downvote(voteDto);
    }

    @Test
    void testGetTopTenQuotes() {
        quoteController.getTopTenQuotes();
        verify(quoteService).getTopTenQuotes();
    }

    @Test
    void testGetWorseTenQuotes() {
        quoteController.getWorseTenQuotes();
        verify(quoteService).getWorseTenQuotes();
    }

    @Test
    void testGetGraphEvolution() {
        quoteController.getGraphEvolution(QUOTE_ID);
        verify(quoteService).getGraphEvolution(QUOTE_ID);
    }
}