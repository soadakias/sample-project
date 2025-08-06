package com.company.demo_app.repositories;

import com.company.demo_app.model.entities.Match;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, BigInteger>, PagingAndSortingRepository<Match, BigInteger> {
    @EntityGraph("matchWithOdds")
    @Query("select m from Match m where m.id = ?1")
    Optional<Match> findByIdWithOdds(@NonNull BigInteger id);

}
