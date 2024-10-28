package service;

import exceptions.TeamAlreadyPlayingException;
import model.Match;
import model.TeamPair;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ScoreBoard {

    private final Map<TeamPair, Match> matches = new ConcurrentHashMap<>();

    public void startMatch(TeamPair teamPair) {
        if (isTeamAlreadyPlaying(teamPair)) {
            throw new TeamAlreadyPlayingException("A team cannot have more than one ongoing match.");
        }
        matches.putIfAbsent(teamPair, new Match(teamPair.getHomeTeam(), teamPair.getAwayTeam()));
    }

    private boolean isTeamAlreadyPlaying(TeamPair teamPair) {
        return matches.values().stream()
                .anyMatch(match -> match.getHomeTeam().equals(teamPair.getHomeTeam()) ||
                        match.getHomeTeam().equals(teamPair.getAwayTeam()) ||
                        match.getAwayTeam().equals(teamPair.getHomeTeam()) ||
                        match.getAwayTeam().equals(teamPair.getAwayTeam()));
    }

    public void updateScore(TeamPair teamPair, int homeScore, int awayScore) {
        Match match = matches.get(teamPair);
        match.updateScore(homeScore, awayScore);
    }

    public void finishMatch(TeamPair teamPair) {
        matches.remove(teamPair);
    }

    public Map<TeamPair, Match> getMatches() {
        return matches;
    }

    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted(Comparator.comparingInt((Match m) -> m.getHomeScore() + m.getAwayScore())
                        .thenComparing(Match::getStartTime).reversed()).collect(Collectors.toList());
    }

    public void printSummary() {
        if (matches.isEmpty()) {
            System.out.println("No ongoing matches.");
            return;
        }

        System.out.println("Current Scoreboard:");
        for (Match match : getSummary()) {
            System.out.println(match.toString());
        }
    }
}
