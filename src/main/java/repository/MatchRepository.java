package repository;

import model.Match;
import model.TeamPair;

import java.util.Map;

public interface MatchRepository {
    void save(Match match);
    void delete(TeamPair teamPair);
    Match findByTeamPair(TeamPair teamPair);
    Map<TeamPair, Match> findAll();
}
