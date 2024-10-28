package service;

import exceptions.InvalidScoreException;
import exceptions.TeamAlreadyPlayingException;
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
        if (homeScore < 0 || awayScore < 0) {
            throw new InvalidScoreException("Scores cannot be negative.");
        }
        Match match = matches.get(teamPair);
        if (match != null) {
            if (match.isSettled() && (homeScore < match.getHomeScore() || awayScore < match.getAwayScore())) {
                throw new InvalidScoreException("Scores of an ongoing match cannod be reduced.");
            }
            match.setHomeScore(homeScore);
            match.setAwayScore(awayScore);
        }
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
}
