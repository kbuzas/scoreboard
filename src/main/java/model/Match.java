package model;

import java.time.LocalDateTime;

public class Match {

    private final WorldCupTeams homeTeam;
    private final WorldCupTeams awayTeam;

    private int homeScore;
    private int awayScore;
    private final LocalDateTime startTime;

    public Match(WorldCupTeams homeTeam, WorldCupTeams awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = LocalDateTime.now();
    }

    public WorldCupTeams getHomeTeam() {
        return homeTeam;
    }

    public WorldCupTeams getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public boolean isSettled() {
        return homeScore > 0 || awayScore > 0;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %d %s", homeTeam, homeScore, awayScore, awayTeam);
    }

    public void updateScore(int homeScore, int awayScore) {

    }
}
