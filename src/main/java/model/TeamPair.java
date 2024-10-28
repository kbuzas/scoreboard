package model;

public class TeamPair {

    private final WorldCupTeams homeTeam;
    private final WorldCupTeams awayTeam;


    public TeamPair(WorldCupTeams homeTeam, WorldCupTeams awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public WorldCupTeams getHomeTeam() {
        return homeTeam;
    }

    public WorldCupTeams getAwayTeam() {
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

