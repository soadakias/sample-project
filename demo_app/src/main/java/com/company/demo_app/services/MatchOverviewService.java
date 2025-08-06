package com.company.demo_app.services;

import com.company.demo_app.model.dtos.MatchDto;
import com.company.demo_app.model.dtos.MatchOddsDto;
import com.company.demo_app.model.entities.Match;
import com.company.demo_app.model.entities.MatchOdds;
import com.company.demo_app.model.mappers.MatchMapper;
import com.company.demo_app.model.mappers.MatchOddsMapper;
import com.company.demo_app.repositories.MatchOddsRepository;
import com.company.demo_app.repositories.MatchRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static com.company.demo_app.utils.NumberFormatUtils.createBigIntegerFromString;

@Service
@Slf4j
public class MatchOverviewService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final MatchOddsMapper matchOddsMapper;
    private final MatchOddsRepository matchOddsRepository;

    public MatchOverviewService(MatchRepository matchRepository, MatchMapper matchMapper, MatchOddsMapper matchOddsMapper, MatchOddsRepository matchOddsRepository) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
        this.matchOddsMapper = matchOddsMapper;
        this.matchOddsRepository = matchOddsRepository;
    }

    public PagedModel<MatchDto> getAllMatchesWithPagination(Pageable pageable) {
        try {
            Page<Match> matches = matchRepository.findAll(pageable);
//            List<MatchDto> matchDtoCollection = matchMapper.toDto(matches.getContent());
            List<MatchDto> matchDtoCollection = matchMapper.fromEntitiesToDtos(matches.getContent());
            return new PagedModel<>(new PageImpl<>(matchDtoCollection, pageable, matches.getTotalElements()));
        } catch (Exception e) {
            log.error("Error while fetching List<Match> with pagination, exception: {}", e.getMessage());
            throw e;
        }
    }

    public MatchDto getMatchById(String id) {
        try {
            BigInteger idNumber = createBigIntegerFromString(id);
            Match match = matchRepository.findById(idNumber)
                    .orElseThrow(() -> new NotFoundException(String.format("Match id: %s not found", id)));
            return matchMapper.fromEntityToDto(match);
        } catch (Exception e) {
            log.error("Error while fetching Match by id, exception: {}", e.getMessage());
            throw e;
        }
    }

    public MatchDto getMatchByIdWithOdds(String id) {
        try {
            BigInteger idNumber = createBigIntegerFromString(id);
            Match match = matchRepository.findByIdWithOdds(idNumber)
                    .orElseThrow(() -> new NotFoundException(String.format("Match id: %s not found", id)));
            return matchMapper.fromEntityToDto(match);
        } catch (Exception e) {
            log.error("Error while fetching Match by id with odds, exception: {}", e.getMessage());
            throw e;
        }
    }

    public PagedModel<MatchOddsDto> getAllOddsWithPagination(Pageable pageable) {
        try {
            Page<MatchOdds> matchOdds = matchOddsRepository.findAll(pageable);
            List<MatchOddsDto> matchOddsDtoCollection = matchOddsMapper.fromEntitiesToDtos(matchOdds.getContent());
            return new PagedModel<>(new PageImpl<>(matchOddsDtoCollection, pageable, matchOdds.getTotalElements()));
        } catch (Exception e) {
            log.error("Error while fetching List<MatchOdds> with pagination, exception: {}", e.getMessage());
            throw e;
        }
    }

    public List<MatchOddsDto> getMatchOddsById(String id) {
        try {
            BigInteger idNumber = createBigIntegerFromString(id);
            List<MatchOdds> matchOdds = matchOddsRepository.findByMatch_Id(idNumber);
            if (matchOdds.isEmpty()) {
                throw new NotFoundException(String.format("Match odds not found for match id: %s", id));
            }
            return matchOddsMapper.fromEntitiesToDtos(matchOdds);
        } catch (Exception e) {
            log.error("Error while fetching MatchOdds by id, exception: {}", e.getMessage());
            throw e;
        }
    }

}
