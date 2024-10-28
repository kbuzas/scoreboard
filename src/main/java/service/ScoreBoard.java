package service;

import model.Match;
import model.TeamPair;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ScoreBoard {

    private final Map<TeamPair, Match> matches = new ConcurrentHashMap<>();

    public void startMatch(TeamPair teamPair) {
        matches.putIfAbsent(teamPair, new Match(teamPair.getHomeTeam(), teamPair.getAwayTeam()));
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

    public List<Match> getSummary(){
        return matches.values().stream()
                .sorted(Comparator.comparingInt((Match m) -> m.getHomeScore() + m.getAwayScore())
                        .thenComparing(Match::getStartTime).reversed()).collect(Collectors.toList());
    }
}
