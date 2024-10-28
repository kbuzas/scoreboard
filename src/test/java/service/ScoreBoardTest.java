package service;

import model.TeamPair;
import org.junit.jupiter.api.Test;
import service.ScoreBoard;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    void testStartMatch(){
        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startMatch("Team A", "Team B");
        assertEquals(1, scoreBoard.getMatches().size());
//        assertEquals("Team A", scoreBoard.getMatches().get(0).getHomeTeam());
//        assertEquals("Team B", scoreBoard.getMatches().get(0).getAwayTeam());
    }

}