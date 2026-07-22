package com.conall.league_of_ireland_tracker.service;

import com.conall.league_of_ireland_tracker.dto.TheSportsDbTeamResponse;
import com.conall.league_of_ireland_tracker.dto.TheSportsDbEventResponse;
import com.conall.league_of_ireland_tracker.model.Team;
import com.conall.league_of_ireland_tracker.model.Fixture;
import com.conall.league_of_ireland_tracker.repository.TeamRepository;
import com.conall.league_of_ireland_tracker.repository.FixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Service
public class FootballApiService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FixtureRepository fixtureRepository;

    private final RestClient restClient = RestClient.create();

    public List<Team> fetchAndSaveTeams() {
        URI url = URI.create("https://www.thesportsdb.com/api/v1/json/123/search_all_teams.php?l=Irish%20Premier%20Division");

        TheSportsDbTeamResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(TheSportsDbTeamResponse.class);

        if (response == null || response.getTeams() == null) {
            throw new RuntimeException("No teams returned from API");
        }

        return response.getTeams().stream()
                .map(dto -> {
                    Team team = new Team();
                    team.setName(dto.getStrTeam());
                    team.setStadium(dto.getStrStadium());

                    try {
                        team.setFounded(Integer.parseInt(dto.getIntFormedYear()));
                    } catch (Exception e) {
                        team.setFounded(0);
                    }

                    return teamRepository.save(team);
                })
                .toList();
    }

    public List<Fixture> fetchAndSaveFixtures() {
        URI url = URI.create("https://www.thesportsdb.com/api/v1/json/123/eventsseason.php?id=4643&s=2026");

        TheSportsDbEventResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(TheSportsDbEventResponse.class);

        if (response == null || response.getEvents() == null) {
            throw new RuntimeException("No fixtures returned from API");
        }

        List<Team> allTeams = teamRepository.findAll();

        return response.getEvents().stream()
                .map(dto -> {
                    Team home = allTeams.stream()
                            .filter(t -> t.getName().equalsIgnoreCase(dto.getStrHomeTeam()))
                            .findFirst()
                            .orElse(null);

                    Team away = allTeams.stream()
                            .filter(t -> t.getName().equalsIgnoreCase(dto.getStrAwayTeam()))
                            .findFirst()
                            .orElse(null);

                    if (home == null || away == null) {
                        return null; // skip fixtures where we don't recognise a team
                    }

                    Fixture fixture = new Fixture();
                    fixture.setHomeTeam(home);
                    fixture.setAwayTeam(away);
                    fixture.setMatchDate(LocalDate.parse(dto.getDateEvent()));
                    fixture.setStatus(dto.getStrStatus());

                    try {
                        fixture.setHomeScore(Integer.parseInt(dto.getIntHomeScore()));
                        fixture.setAwayScore(Integer.parseInt(dto.getIntAwayScore()));
                    } catch (Exception e) {
                        fixture.setHomeScore(null);
                        fixture.setAwayScore(null);
                    }

                    return fixtureRepository.save(fixture);
                })
                .filter(f -> f != null)
                .toList();
    }
}