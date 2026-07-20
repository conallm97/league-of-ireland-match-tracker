package com.conall.league_of_ireland_tracker;

import com.conall.league_of_ireland_tracker.model.Team;
import com.conall.league_of_ireland_tracker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TeamRepository teamRepository;

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

    @GetMapping("/api/teams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}