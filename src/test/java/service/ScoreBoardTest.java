package service;

import model.Match;
import model.TeamPair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ScoreBoard;

import java.util.Map;

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

    @Test
    void testFinishMatch(){
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch(teamPair);
        scoreBoard.finishMatch(teamPair);

        assertEquals(0, scoreBoard.getMatches().size());
    }

    @Test
    void testMatchSummary(){
        ScoreBoard scoreBoard = new ScoreBoard();
        TeamPair pair1 = new TeamPair("Team A", "Team B");
        scoreBoard.startMatch(pair1);
        scoreBoard.updateScore(pair1, 1, 0);

        TeamPair pair2 = new TeamPair("Team C", "Team D");
        scoreBoard.startMatch(pair2);
        scoreBoard.updateScore(pair2, 2, 1);

        Map<TeamPair, Match> summary = scoreBoard.getSummary();
        assertEquals(2, summary.size());
        assertEquals("Team C 2 - 1 Team D", summary.get(pair2).toString());
        assertEquals("Team A 1 - 0 Team B", summary.get(pair1).toString());
    }
}