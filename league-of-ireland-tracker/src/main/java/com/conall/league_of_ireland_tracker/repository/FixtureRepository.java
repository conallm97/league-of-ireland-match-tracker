package com.conall.league_of_ireland_tracker.repository;

import com.conall.league_of_ireland_tracker.model.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {
}