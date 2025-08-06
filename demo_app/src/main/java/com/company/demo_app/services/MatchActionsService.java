package com.company.demo_app.services;

import com.company.demo_app.model.dtos.MatchDto;
import com.company.demo_app.model.dtos.MatchOddsDto;
import com.company.demo_app.model.entities.Match;
import com.company.demo_app.model.entities.MatchOdds;
import com.company.demo_app.model.mappers.MatchMapper;
import com.company.demo_app.model.mappers.MatchOddsMapper;
import com.company.demo_app.repositories.MatchOddsRepository;
import com.company.demo_app.repositories.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static com.company.demo_app.utils.NumberFormatUtils.createBigIntegerFromString;

@Service
@Slf4j
public class MatchActionsService {

    private final MatchRepository matchRepository;
    private final MatchOddsRepository matchOddsRepository;
    private final MatchMapper matchMapper;
    private final MatchOddsMapper matchOddsMapper;

    public MatchActionsService(MatchRepository matchRepository, MatchOddsRepository matchOddsRepository,
            MatchMapper matchMapper, MatchOddsMapper matchOddsMapper) {
        this.matchRepository = matchRepository;
        this.matchOddsRepository = matchOddsRepository;
        this.matchMapper = matchMapper;
        this.matchOddsMapper = matchOddsMapper;
    }

    @Transactional
    public String createMatch(MatchDto matchDto) {
        try {
            Match match = matchMapper.fromDtoToEntity(matchDto);
            List<MatchOdds> matchOddsList = match.getMatchOdds();
            match.setMatchOdds(null);
            matchRepository.save(match);
            matchOddsList.forEach(odd -> odd.setMatch(Match.builder().id(match.getId()).build()));
            matchOddsRepository.saveAll(matchOddsList);
            return match.getId().toString();
        } catch (Exception e) {
            log.error("Error while creating match, exception: {}", e.getMessage());
            throw e;
        }
    }

    public void updateMatch(MatchDto matchDto) {
        try {
            Match match = matchMapper.fromDtoToEntity(matchDto);
            matchRepository.save(match);
        } catch (Exception e) {
            log.error("Error while updating match, exception: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteMatchById(String id) {
        try {
            BigInteger idNumber = createBigIntegerFromString(id);
            matchRepository.deleteById(idNumber);
        } catch (Exception e) {
            log.error("Error while deleting match, exception: {}", e.getMessage());
            throw e;
        }
    }

    public String createMatchOdds(MatchOddsDto matchOddsDto) {
        try {
            MatchOdds matchOdds = matchOddsMapper.fromDtoToEntity(matchOddsDto);
            matchOddsRepository.save(matchOdds);
            return matchOdds.getId().toString();
        } catch (Exception e) {
            log.error("Error while creating matchOdds, exception: {}", e.getMessage());
            throw e;
        }
    }

    public void updateMatchOdds(MatchOddsDto matchOddsDto) {
        try {
            MatchOdds matchOdds = matchOddsMapper.fromDtoToEntity(matchOddsDto);
            matchOddsRepository.save(matchOdds);
        } catch (Exception e) {
            log.error("Error while updating matchOdds, exception: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteMatchOddsById(String id) {
        try {
            BigInteger idNumber = createBigIntegerFromString(id);
            matchOddsRepository.deleteById(idNumber);
        } catch (Exception e) {
            log.error("Error while deleting matchOdds, exception: {}", e.getMessage());
            throw e;
        }
    }

}
