package com.company.demo_app.services;

import com.company.demo_app.model.entities.Specifier;
import com.company.demo_app.model.entities.Sport;
import com.company.demo_app.repositories.SpecifierRepository;
import com.company.demo_app.repositories.SportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConfigurationService {

    private final SportRepository sportRepository;
    private final SpecifierRepository specifierRepository;

    public ConfigurationService(SportRepository sportRepository, SpecifierRepository specifierRepository) {
        this.sportRepository = sportRepository;
        this.specifierRepository = specifierRepository;
    }

    public List<Sport> getAllSports() {
        try {
            return sportRepository.findAll();
        } catch (Exception e) {
            log.error("Error while fetching List<Sport>, exception: {}", e.getMessage());
            throw e;
        }
    }

    public void createSport(Sport sport) {
        try {
            sportRepository.save(sport);
        } catch (Exception e) {
            log.error("Error while saving Sport, exception: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteSport(Sport sport) {
        try {
            sportRepository.delete(sport);
        } catch (Exception e) {
            log.error("Error while deleting Sport, exception: {}", e.getMessage());
            throw e;
        }
    }

    public List<Specifier> getAllSpecifiers() {
        try {
            return specifierRepository.findAll();
        } catch (Exception e) {
            log.error("Error while fetching List<Specifier>, exception: {}", e.getMessage());
            throw e;
        }
    }

    public void createSpecifier(Specifier specifier) {
        try {
            specifierRepository.save(specifier);
        } catch (Exception e) {
            log.error("Error while saving Specifier, exception: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteSpecifier(Specifier specifier) {
        try {
            specifierRepository.delete(specifier);
        } catch (Exception e) {
            log.error("Error while deleting Specifier, exception: {}", e.getMessage());
            throw e;
        }
    }

}
