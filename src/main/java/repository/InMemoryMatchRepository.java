package repository;

import model.Match;
import model.TeamPair;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryMatchRepository implements MatchRepository {
    private final Map<TeamPair, Match> matches = new ConcurrentHashMap<>();

    @Override
    public void save(Match match) {
        matches.putIfAbsent(new TeamPair(match.getHomeTeam(), match.getAwayTeam()), match);
    }

    @Override
    public void delete(TeamPair teamPair) {
        matches.remove(teamPair);
    }

    @Override
    public Match findByTeamPair(TeamPair teamPair) {
        return matches.get(teamPair);
    }

    @Override
    public Map<TeamPair, Match> findAll() {
        return matches;
    }
}
