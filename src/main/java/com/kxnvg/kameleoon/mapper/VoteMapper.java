package com.kxnvg.kameleoon.mapper;

import com.kxnvg.kameleoon.dto.VoteDto;
import com.kxnvg.kameleoon.entity.Quote;
import com.kxnvg.kameleoon.entity.User;
import com.kxnvg.kameleoon.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteMapper {

    @Mapping(target = "userId",  source = "user", qualifiedByName = "mapUserToId")
    @Mapping(target = "quoteId",  source = "quote", qualifiedByName = "mapQuoteToId")
    VoteDto toDto(Vote vote);

    @Mapping(target = "user",  source = "userId", qualifiedByName = "mapIdToUser")
    @Mapping(target = "quote",  source = "quoteId", qualifiedByName = "mapIdToQuote")
    Vote toEntity(VoteDto voteDto);

    @Named("mapIdToUser")
    default User mapIdToUser(Long userId) {
        return User.builder()
                .id(userId)
                .build();
    }

    @Named("mapIdToQuote")
    default Quote mapIdTpQuote(Long quoteId) {
        return Quote.builder()
                .id(quoteId)
                .build();
    }

    @Named("mapUserToId")
    default Long mapUserToId(User user) {
        return user.getId();
    }

    @Named("mapQuoteToId")
    default Long mapQuoteToId(Quote quote) {
        return quote.getId();
    }
}
