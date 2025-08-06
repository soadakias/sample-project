package com.company.demo_app.model.dtos;

import com.company.demo_app.enums.SpecifierType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class MatchOddsDto implements Serializable {
    private BigInteger id;
    private BigInteger matchId;
    private SpecifierType specifier;
    private BigDecimal odd;
}
