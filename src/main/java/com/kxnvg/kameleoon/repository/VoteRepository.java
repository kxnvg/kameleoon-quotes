package com.kxnvg.kameleoon.repository;

import com.kxnvg.kameleoon.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select v from Vote v where v.quote.id = :quoteId order by v.votedAt")
    List<Vote> findAllQuoteVotes(@Param("quoteId") long quoteId);
}
