package com.conall.league_of_ireland_tracker.service;

import com.conall.league_of_ireland_tracker.dto.TheSportsDbTeamResponse;
import com.conall.league_of_ireland_tracker.model.Team;
import com.conall.league_of_ireland_tracker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Service
public class FootballApiService {

    @Autowired
    private TeamRepository teamRepository;

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
}