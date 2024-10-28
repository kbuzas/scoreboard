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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamPair teamPair = (TeamPair) o;

        if (!getHomeTeam().equals(teamPair.getHomeTeam())) return false;
        return getAwayTeam().equals(teamPair.getAwayTeam());
    }

    @Override
    public int hashCode() {
        int result = getHomeTeam().hashCode();
        result = 31 * result + getAwayTeam().hashCode();
        return result;
    }
}

