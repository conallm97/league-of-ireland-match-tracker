package com.conall.league_of_ireland_tracker.service;

import com.conall.league_of_ireland_tracker.dto.StandingsEntry;
import com.conall.league_of_ireland_tracker.model.Fixture;
import com.conall.league_of_ireland_tracker.model.Team;
import com.conall.league_of_ireland_tracker.repository.FixtureRepository;
import com.conall.league_of_ireland_tracker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StandingsService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FixtureRepository fixtureRepository;

    public List<StandingsEntry> calculateStandings() {
        List<Team> teams = teamRepository.findAll();
        List<Fixture> fixtures = fixtureRepository.findAll();

        Map<Long, StandingsEntry> table = new HashMap<>();
        for (Team team : teams) {
            table.put(team.getId(), new StandingsEntry(team.getName()));
        }

        for (Fixture fixture : fixtures) {
            // Only count finished matches with actual scores
            if (!"FT".equalsIgnoreCase(fixture.getStatus())) continue;
            if (fixture.getHomeScore() == null || fixture.getAwayScore() == null) continue;

            Long homeId = fixture.getHomeTeam().getId();
            Long awayId = fixture.getAwayTeam().getId();

            StandingsEntry homeEntry = table.get(homeId);
            StandingsEntry awayEntry = table.get(awayId);

            if (homeEntry == null || awayEntry == null) continue;

            homeEntry.addResult(fixture.getHomeScore(), fixture.getAwayScore());
            awayEntry.addResult(fixture.getAwayScore(), fixture.getHomeScore());
        }

        return table.values().stream()
                .sorted(
                        Comparator.comparingInt(StandingsEntry::getPoints).reversed()
                                .thenComparing(Comparator.comparingInt(StandingsEntry::getGoalDifference).reversed())
                                .thenComparing(Comparator.comparingInt(StandingsEntry::getGoalsFor).reversed())
                )
                .toList();
    }
}