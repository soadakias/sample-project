package com.company.demo_app.model.entities;

import com.company.demo_app.enums.SpecifierType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match_odds")
public class MatchOdds {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odds_seq")
    @SequenceGenerator(name = "match_odds_seq", sequenceName = "match_odds_id_seq", allocationSize = 1)
    @Column(name = "id")
    private BigInteger id;

//    @Column(name = "match_id")
//    private BigInteger matchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    @ToString.Exclude
    private Match match;

    @Column(name = "specifier")
    private SpecifierType specifier;

    @Column(name = "odd")
    private BigDecimal odd;
}
