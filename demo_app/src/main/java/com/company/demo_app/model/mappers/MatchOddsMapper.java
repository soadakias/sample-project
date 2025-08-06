package com.company.demo_app.model.mappers;

import com.company.demo_app.model.dtos.MatchOddsDto;
import com.company.demo_app.model.entities.Match;
import com.company.demo_app.model.entities.MatchOdds;
import com.company.demo_app.repositories.MatchRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MatchOddsMapper {

    @Autowired
    MatchRepository matchRepository;

    @Named("fromMatchOddsDtoToMatchOddsEntity")
//    @Mapping(target = "match", expression = "java(matchRepository.getReferenceById(matchOddsDto.getMatchId()))")
    @Mapping(source = "matchId", target = "match", qualifiedByName = "mapMatch", defaultExpression = "java(null)")
    public abstract MatchOdds fromDtoToEntity(MatchOddsDto matchOddsDto);

    @Named("fromMatchOddsEntityToMatchOddsDto")
    @Mapping(source = "match.id", target = "matchId")
    public abstract MatchOddsDto fromEntityToDto(MatchOdds matchOdds);

    @IterableMapping(qualifiedByName = "fromMatchOddsDtoToMatchOddsEntity")
    public abstract List<MatchOdds> fromDtosToEntities(List<MatchOddsDto> matchOddsDtoList);

    @IterableMapping(qualifiedByName = "fromMatchOddsEntityToMatchOddsDto")
    public abstract List<MatchOddsDto> fromEntitiesToDtos(List<MatchOdds> matchOddsList);

    @Named("mapMatch")
    protected Match mapMatch(BigInteger matchId){
        return matchRepository.getReferenceById(matchId);
    }

}
