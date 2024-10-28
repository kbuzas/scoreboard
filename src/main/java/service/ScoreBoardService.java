package service;

import exceptions.TeamAlreadyPlayingException;
import model.Match;
import model.TeamPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MatchRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public record ScoreBoardService(MatchRepository matchRepository) {

    @Autowired
    public ScoreBoardService {
    }

    public void startMatch(TeamPair teamPair) {
        if (isTeamAlreadyPlaying(teamPair)) {
            throw new TeamAlreadyPlayingException("A team cannot have more than one ongoing match.");
        }
        Match match = new Match(teamPair.getHomeTeam(), teamPair.getAwayTeam());
        matchRepository.save(match);
    }

    private boolean isTeamAlreadyPlaying(TeamPair teamPair) {
        return matchRepository.findAll().values().stream()
                .anyMatch(match -> match.getHomeTeam().equals(teamPair.getHomeTeam()) ||
                        match.getHomeTeam().equals(teamPair.getAwayTeam()) ||
                        match.getAwayTeam().equals(teamPair.getHomeTeam()) ||
                        match.getAwayTeam().equals(teamPair.getAwayTeam()));
    }

    public void updateScore(TeamPair teamPair, int homeScore, int awayScore) {
        Match match = matchRepository.findByTeamPair(teamPair);
        match.updateScore(homeScore, awayScore);
    }

    public void finishMatch(TeamPair teamPair) {
        matchRepository.delete(teamPair);
    }

    public Map<TeamPair, Match> getMatches() {
        return matchRepository.findAll();
    }

    public List<Match> getSummary() {
        return matchRepository.findAll().values().stream()
                .sorted(Comparator.comparingInt((Match m) -> m.getHomeScore() + m.getAwayScore())
                        .thenComparing(Match::getStartTime).reversed()).collect(Collectors.toList());
    }

    public void printSummary() {
        if (getSummary().isEmpty()) {
            System.out.println("No ongoing matches.");
            return;
        }

        System.out.println("Current Scoreboard:");
        for (Match match : getSummary()) {
            System.out.println(match.toString());
        }
    }
}
