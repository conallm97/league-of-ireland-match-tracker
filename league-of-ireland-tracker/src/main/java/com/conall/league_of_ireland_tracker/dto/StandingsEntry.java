package com.conall.league_of_ireland_tracker.dto;

public class StandingsEntry {
    private String teamName;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private int points;

    public StandingsEntry(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() { return teamName; }
    public int getPlayed() { return played; }
    public int getWon() { return won; }
    public int getDrawn() { return drawn; }
    public int getLost() { return lost; }
    public int getGoalsFor() { return goalsFor; }
    public int getGoalsAgainst() { return goalsAgainst; }
    public int getGoalDifference() { return goalDifference; }
    public int getPoints() { return points; }

    public void addResult(int goalsScored, int goalsConceded) {
        played++;
        goalsFor += goalsScored;
        goalsAgainst += goalsConceded;
        goalDifference = goalsFor - goalsAgainst;

        if (goalsScored > goalsConceded) {
            won++;
            points += 3;
        } else if (goalsScored == goalsConceded) {
            drawn++;
            points += 1;
        } else {
            lost++;
        }
    }
}