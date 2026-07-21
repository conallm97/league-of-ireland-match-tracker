package com.conall.league_of_ireland_tracker.dto;

import java.util.List;

public class TheSportsDbTeamResponse {
    private List<TeamDto> teams;

    public List<TeamDto> getTeams() { return teams; }
    public void setTeams(List<TeamDto> teams) { this.teams = teams; }

    public static class TeamDto {
        private String idTeam;
        private String strTeam;
        private String strStadium;
        private String intFormedYear;
        private String strBadge;

        public String getIdTeam() { return idTeam; }
        public void setIdTeam(String idTeam) { this.idTeam = idTeam; }

        public String getStrTeam() { return strTeam; }
        public void setStrTeam(String strTeam) { this.strTeam = strTeam; }

        public String getStrStadium() { return strStadium; }
        public void setStrStadium(String strStadium) { this.strStadium = strStadium; }

        public String getIntFormedYear() { return intFormedYear; }
        public void setIntFormedYear(String intFormedYear) { this.intFormedYear = intFormedYear; }

        public String getStrBadge() { return strBadge; }
        public void setStrBadge(String strBadge) { this.strBadge = strBadge; }
    }
}