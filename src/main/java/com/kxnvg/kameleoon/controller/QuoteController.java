package com.kxnvg.kameleoon.controller;

import com.kxnvg.kameleoon.dto.QuoteDto;
import com.kxnvg.kameleoon.service.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/quotes")
@RequiredArgsConstructor
@Slf4j
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("/{id}")
    public QuoteDto getQuote(@PathVariable("id") Long quoteId) {
        log.info("Received request to get quote with id={}", quoteId);
        return quoteService.getQuote(quoteId);
    }

    @GetMapping
    public QuoteDto getRandomQuote() {
        log.info("Received request to get random quote");
        return quoteService.getRandomQuote();
    }

    @PostMapping
    public void createQuote(@RequestBody @Valid QuoteDto quoteDto) {
        log.info("Received request to create new quote by author with id={}", quoteDto.getUserId());
        quoteService.createQuote(quoteDto);
    }

    @PutMapping
    public QuoteDto updateQuote(@RequestBody @Valid QuoteDto quoteDto) {
        log.info("Received request to update quote with id={}", quoteDto.getId());
        return quoteService.updateQuote(quoteDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuote(@PathVariable("id") Long quoteId) {
        log.info("Received request to delete quote with id={}", quoteId);
        return quoteService.deleteQuote(quoteId);
    }

    @PutMapping("/{id}/upvote")
    public void upvote(@PathVariable("id") Long quoteId) {
        log.info("Received request to upvote quote with id={}", quoteId);
        quoteService.upvote(quoteId);
    }

    @PutMapping("/{id}/downvote")
    public void downvote(@PathVariable("id") Long quoteId) {
        log.info("Received request to downvote quote with id={}", quoteId);
        quoteService.downvote(quoteId);
    }

    @GetMapping("/top")
    public List<QuoteDto> getTopTenQuotes() {
        log.info("Received request to get top 10 quotes");
        return quoteService.getTopTenQuotes();
    }

    @GetMapping("/worse")
    public List<QuoteDto> getWorseTenQuotes() {
        log.info("Received request to get worse 10 quotes");
        return quoteService.getWorseTenQuotes();
    }
}
