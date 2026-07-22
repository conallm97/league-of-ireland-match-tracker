package com.conall.league_of_ireland_tracker.controller;

import com.conall.league_of_ireland_tracker.dto.StandingsEntry;
import com.conall.league_of_ireland_tracker.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StandingsController {

    @Autowired
    private StandingsService standingsService;

    @GetMapping("/api/standings")
    public List<StandingsEntry> getStandings() {
        return standingsService.calculateStandings();
    }
}