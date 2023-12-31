package com.kxnvg.kameleoon.mapper;

import com.kxnvg.kameleoon.dto.QuoteDto;
import com.kxnvg.kameleoon.entity.Quote;
import com.kxnvg.kameleoon.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuoteMapper {

    @Mapping(target = "userId", source = "user", qualifiedByName = "mapUserToId")
    QuoteDto toDto(Quote quote);

    @Mapping(target = "user",  source = "userId", qualifiedByName = "mapIdToUser")
    Quote toEntity(QuoteDto quoteDto);

    @Named("mapIdToUser")
    default User mapIdToUser(Long userId) {
        return User.builder()
                .id(userId)
                .build();
    }

    @Named("mapUserToId")
    default Long mapUserToId(User user) {
        return user.getId();
    }
}
