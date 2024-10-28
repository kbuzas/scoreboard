package model;

public class TeamPair {

    private final String homeTeam;
    private final String awayTeam;


    public TeamPair(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

}

