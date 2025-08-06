package com.company.demo_app.repositories;

import com.company.demo_app.model.entities.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, BigInteger> {
    List<MatchOdds> findByMatch_Id(@NonNull BigInteger id);
}
