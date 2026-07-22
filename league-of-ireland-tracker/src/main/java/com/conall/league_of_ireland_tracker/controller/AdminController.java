package com.conall.league_of_ireland_tracker.controller;

import com.conall.league_of_ireland_tracker.model.Team;
import com.conall.league_of_ireland_tracker.model.Fixture;
import com.conall.league_of_ireland_tracker.service.FootballApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private FootballApiService footballApiService;

    @GetMapping("/api/import-teams")
    public List<Team> importTeams() {
        return footballApiService.fetchAndSaveTeams();
    }

    @GetMapping("/api/import-fixtures")
    public List<Fixture> importFixtures() {
        return footballApiService.fetchAndSaveFixtures();
    }
}