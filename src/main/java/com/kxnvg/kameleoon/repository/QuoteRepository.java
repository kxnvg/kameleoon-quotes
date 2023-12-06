package com.kxnvg.kameleoon.repository;

import com.kxnvg.kameleoon.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("select q from Quote q order by q.votes")
    List<Quote> getWorseTenQuotes();

    @Query("select q from Quote q order by q.votes desc")
    List<Quote> getTopTenQuotes();

}
