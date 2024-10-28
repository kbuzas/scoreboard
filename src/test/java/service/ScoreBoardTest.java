package service;

import model.Match;
import model.TeamPair;
import org.junit.jupiter.api.Test;
import service.ScoreBoard;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    void testStartMatch(){
        ScoreBoard scoreBoard = new ScoreBoard();

        TeamPair teamPair = new TeamPair("Team A", "Team B");
        scoreBoard.startMatch("Team A", "Team B");
        assertEquals(1, scoreBoard.getMatches().size());
        assertEquals("Team A", scoreBoard.getMatches().get(teamPair).getHomeTeam());
        assertEquals("Team B", scoreBoard.getMatches().get(teamPair).getAwayTeam());
    }

    @Test
    void updateScore(){
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Team A", "Team B");
        scoreBoard.updateScore("Team A", "Team B", 2, 3);

        TeamPair teamPair = new TeamPair("Team A", "Team B");

        Match match = scoreBoard.getMatches().get(teamPair);
        assertEquals(2, match.getHomeScore());
        assertEquals(3, match.getAwayScore());
    }

}