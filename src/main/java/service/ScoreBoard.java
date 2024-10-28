package service;

import model.Match;
import model.TeamPair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoard {

    private final Map<TeamPair, Match> matches = new ConcurrentHashMap<>();

    public void startMatch(String homeTeam, String awayTeam) {
        TeamPair teamPair = new TeamPair(homeTeam, awayTeam);
        matches.putIfAbsent(teamPair, new Match(homeTeam, awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        TeamPair teamPair = new TeamPair(homeTeam, awayTeam);
        Match match = matches.get(teamPair);
        if (match != null) {
            match.setHomeScore(homeScore);
            match.setAwayScore(awayScore);
        }
    }

    public Map<TeamPair, Match> getMatches() {
        return matches;
    }
}
