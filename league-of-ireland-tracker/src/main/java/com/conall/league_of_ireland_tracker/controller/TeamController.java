package com.conall.league_of_ireland_tracker.controller;

import com.conall.league_of_ireland_tracker.model.Team;
import com.conall.league_of_ireland_tracker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/api/teams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}