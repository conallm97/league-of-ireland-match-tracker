package com.conall.league_of_ireland_tracker;

import com.conall.league_of_ireland_tracker.model.Team;
import com.conall.league_of_ireland_tracker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.conall.league_of_ireland_tracker.service.FootballApiService;
import com.conall.league_of_ireland_tracker.model.Fixture;
import com.conall.league_of_ireland_tracker.repository.FixtureRepository;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FixtureRepository fixtureRepository;

    @GetMapping("/api/test")
    public String test() {
        return "League of Ireland Tracker backend is alive!";
    }

    @GetMapping("/api/add-test-team")
    public Team addTestTeam() {
        Team team = new Team();
        team.setName("Shamrock Rovers");
        team.setStadium("Tallaght Stadium");
        team.setFounded(1899);
        return teamRepository.save(team);
    }

    @GetMapping("/api/add-test-team-2")
    public Team addTestTeam2() {
        Team team = new Team();
        team.setName("Bohemians");
        team.setStadium("Dalymount Park");
        team.setFounded(1890);
        return teamRepository.save(team);
    }

    @GetMapping("/api/teams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/api/add-test-fixture")
    public Fixture addTestFixture() {
        Team home = teamRepository.findById(1L).orElseThrow();
        Team away = teamRepository.findById(2L).orElseThrow();

        Fixture fixture = new Fixture();
        fixture.setHomeTeam(home);
        fixture.setAwayTeam(away);
        fixture.setMatchDate(LocalDate.of(2026, 8, 15));
        fixture.setStatus("SCHEDULED");

        return fixtureRepository.save(fixture);
    }

    @GetMapping("/api/fixtures")
    public List<Fixture> getAllFixtures() {
        return fixtureRepository.findAll();
    }

    @Autowired
    private FootballApiService footballApiService;

    @GetMapping("/api/import-teams")
    public List<Team> importTeams() {
        return footballApiService.fetchAndSaveTeams();
    }
}
