package com.company.demo_app.repositories;

import com.company.demo_app.model.entities.Specifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecifierRepository extends JpaRepository<Specifier, String> {
}
