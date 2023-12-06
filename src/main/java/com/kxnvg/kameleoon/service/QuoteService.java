package com.kxnvg.kameleoon.service;

import com.kxnvg.kameleoon.dto.QuoteDto;
import com.kxnvg.kameleoon.entity.Quote;
import com.kxnvg.kameleoon.mapper.QuoteMapper;
import com.kxnvg.kameleoon.repository.QuoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final QuoteMapper quoteMapper;

    @Transactional(readOnly = true)
    public QuoteDto getQuote(Long quoteId) {
        Quote quote = takeQuoteFromDB(quoteId);
        log.info("Quote with id={} was taken from DB successfully", quoteId);
        return quoteMapper.toDto(quote);
    }

    @Transactional(readOnly = true)
    public QuoteDto getRandomQuote() {
        long quoteNumber = quoteRepository.count();
        long quoteId = new Random().nextLong(quoteNumber);

        Quote quote = quoteRepository.findById(quoteId).get();
        log.info("Quote with id={} was taken from DB successfully", quoteId);
        return quoteMapper.toDto(quote);
    }

    @Transactional
    public void createQuote(QuoteDto quoteDto) {
        quoteDto.setVotes(0L);
        quoteRepository.save(quoteMapper.toEntity(quoteDto));
        log.info("New quote with author's id={} was saved successfully", quoteDto.getUserId());
    }

    @Transactional
    public QuoteDto updateQuote(QuoteDto newQuoteDto) {
        Quote quote = takeQuoteFromDB(newQuoteDto.getId());
        quote.setContent(newQuoteDto.getContent());
        log.info("Quote with id={} was updated successfully", quote.getId());
        return quoteMapper.toDto(quote);
    }

    @Transactional
    public boolean deleteQuote(Long quoteId) {
        Optional<Quote> maybeQuote = quoteRepository.findById(quoteId);
        if (maybeQuote.isPresent()) {
            quoteRepository.deleteById(quoteId);
            log.info("Quote with id={} was deleted from DB successfully", quoteId);
            return true;
        } else {
            log.info("Quote with id={} was not find in DB", quoteId);
            return false;
        }
    }

    @Transactional
    public void upvote(Long quoteId) {
        Quote quote = takeQuoteFromDB(quoteId);
        quote.increment();
        log.info("Votes of quote with id={} was increment", quoteId);
    }

    @Transactional
    public void downvote(Long quoteId) {
        Quote quote = takeQuoteFromDB(quoteId);
        quote.decrement();
        log.info("Votes of quote with id={} was decrement", quoteId);
    }


    @Transactional(readOnly = true)
    public List<QuoteDto> getTopTenQuotes() {
        List<Quote> topQuotes = quoteRepository.getTopTenQuotes();
        log.info("Top quotes was taken from DB successfully");
        return topQuotes.stream().
                map(quoteMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<QuoteDto> getWorseTenQuotes() {
        List<Quote> topQuotes = quoteRepository.getWorseTenQuotes();
        log.info("Worse quotes was taken from DB successfully");
        return topQuotes.stream().
                map(quoteMapper::toDto)
                .toList();
    }

    private Quote takeQuoteFromDB(Long quoteId) {
        Optional<Quote> maybeQuote = quoteRepository.findById(quoteId);
        return maybeQuote
                .orElseThrow(() -> new EntityNotFoundException(String.format("Quote with id=%s is not found", quoteId)));
    }
}
