package com.conall.league_of_ireland_tracker.controller;

import com.conall.league_of_ireland_tracker.model.Fixture;
import com.conall.league_of_ireland_tracker.repository.FixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FixtureController {

    @Autowired
    private FixtureRepository fixtureRepository;

    @GetMapping("/api/fixtures")
    public List<Fixture> getAllFixtures() {
        return fixtureRepository.findAll();
    }
}