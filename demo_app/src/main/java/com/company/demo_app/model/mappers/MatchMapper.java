package com.company.demo_app.model.mappers;

import com.company.demo_app.model.dtos.MatchDto;
import com.company.demo_app.model.entities.Match;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MatchOddsMapper.class})
public interface MatchMapper {
    @Named("fromMatchDtoToMatchEntity")
    @Mapping(source = "matchOddsDto", target = "matchOdds")
    Match fromDtoToEntity(MatchDto matchDto);

    @Named("fromMatchEntityToMatchDto")
    @Mapping(source = "matchOdds", target = "matchOddsDto")
    MatchDto fromEntityToDto(Match match);

    @IterableMapping(qualifiedByName = "fromMatchDtoToMatchEntity")
    List<Match> fromDtosToEntities(List<MatchDto> matchDtoList);

    @IterableMapping(qualifiedByName = "fromMatchEntityToMatchDto")
    List<MatchDto> fromEntitiesToDtos(List<Match> matchList);

}