package service;

import model.Match;
import model.TeamPair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ScoreBoard;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {
    TeamPair teamPair;

    @BeforeEach
    void setup() {
        teamPair = new TeamPair("Team A", "Team B");
    }

    @Test
    void testStartMatch() {
        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startMatch(teamPair);
        assertEquals(1, scoreBoard.getMatches().size());
        assertEquals("Team A", scoreBoard.getMatches().get(teamPair).getHomeTeam());
        assertEquals("Team B", scoreBoard.getMatches().get(teamPair).getAwayTeam());
    }

    @Test
    void testUpdateScore() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch(teamPair);
        scoreBoard.updateScore(teamPair, 2, 3);


        Match match = scoreBoard.getMatches().get(teamPair);
        assertEquals(2, match.getHomeScore());
        assertEquals(3, match.getAwayScore());
    }

}