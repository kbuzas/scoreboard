package service;

import model.Match;
import model.TeamPair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoard {

    private final Map<TeamPair, Match> matches = new ConcurrentHashMap<>();

    public void startMatch(TeamPair teamPair) {
        matches.putIfAbsent(teamPair, new Match(teamPair.getHomeTeam(), teamPair.getHomeTeam()));
    }

    public void updateScore(TeamPair teamPair, int homeScore, int awayScore) {
        Match match = matches.get(teamPair);
        if (match != null) {
            match.setHomeScore(homeScore);
            match.setAwayScore(awayScore);
        }
    }

    public void finishMatch(TeamPair teamPair){
        matches.remove(teamPair);
    }

    public Map<TeamPair, Match> getMatches() {
        return matches;
    }
}
