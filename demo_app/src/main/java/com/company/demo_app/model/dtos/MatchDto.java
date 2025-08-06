package com.company.demo_app.model.dtos;

import com.company.demo_app.enums.SportType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class MatchDto implements Serializable {
    private BigInteger id;
    private String description;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private SportType sport;
    private List<MatchOddsDto> matchOddsDto;
}
