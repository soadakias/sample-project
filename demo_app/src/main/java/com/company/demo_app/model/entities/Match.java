package com.company.demo_app.model.entities;

import com.company.demo_app.enums.SportType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match")
@NamedEntityGraphs(
        @NamedEntityGraph(name = "matchWithOdds", attributeNodes = {
                @NamedAttributeNode("matchOdds")
        })
)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_seq")
    @SequenceGenerator(name = "match_seq", sequenceName = "match_id_seq", allocationSize = 1)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_time")
    private LocalTime matchTime;

    @Column(name = "sport")
    @Enumerated(EnumType.STRING)
    private SportType sport;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MatchOdds> matchOdds;

}
