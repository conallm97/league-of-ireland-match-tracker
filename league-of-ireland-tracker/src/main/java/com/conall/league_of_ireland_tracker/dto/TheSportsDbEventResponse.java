package com.conall.league_of_ireland_tracker.dto;

import java.util.List;

public class TheSportsDbEventResponse {
    private List<EventDto> events;

    public List<EventDto> getEvents() { return events; }
    public void setEvents(List<EventDto> events) { this.events = events; }

    public static class EventDto {
        private String idEvent;
        private String strHomeTeam;
        private String strAwayTeam;
        private String dateEvent;
        private String intHomeScore;
        private String intAwayScore;
        private String strStatus;

        public String getIdEvent() { return idEvent; }
        public void setIdEvent(String idEvent) { this.idEvent = idEvent; }

        public String getStrHomeTeam() { return strHomeTeam; }
        public void setStrHomeTeam(String strHomeTeam) { this.strHomeTeam = strHomeTeam; }

        public String getStrAwayTeam() { return strAwayTeam; }
        public void setStrAwayTeam(String strAwayTeam) { this.strAwayTeam = strAwayTeam; }

        public String getDateEvent() { return dateEvent; }
        public void setDateEvent(String dateEvent) { this.dateEvent = dateEvent; }

        public String getIntHomeScore() { return intHomeScore; }
        public void setIntHomeScore(String intHomeScore) { this.intHomeScore = intHomeScore; }

        public String getIntAwayScore() { return intAwayScore; }
        public void setIntAwayScore(String intAwayScore) { this.intAwayScore = intAwayScore; }

        public String getStrStatus() { return strStatus; }
        public void setStrStatus(String strStatus) { this.strStatus = strStatus; }
    }
}