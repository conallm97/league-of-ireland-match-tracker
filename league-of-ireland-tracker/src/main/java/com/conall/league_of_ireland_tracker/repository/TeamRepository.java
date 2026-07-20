package com.conall.league_of_ireland_tracker.repository;

import com.conall.league_of_ireland_tracker.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}