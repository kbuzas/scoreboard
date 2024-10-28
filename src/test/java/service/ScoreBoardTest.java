package service;

import exceptions.InvalidScoreException;
import exceptions.TeamAlreadyPlayingException;
import model.Match;
import model.TeamPair;
import model.WorldCupTeams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreBoardTest {
    TeamPair teamPair;
    ScoreBoard scoreBoard;

    @BeforeEach
    void setup() {
        teamPair = new TeamPair(WorldCupTeams.ARGENTINA, WorldCupTeams.BRAZIL);
        scoreBoard = new ScoreBoard();

    }

    @Test
    void testStartMatch() {

        scoreBoard.startMatch(teamPair);
        assertEquals(1, scoreBoard.getMatches().size());
        assertEquals(WorldCupTeams.ARGENTINA, scoreBoard.getMatches().get(teamPair).getHomeTeam());
        assertEquals(WorldCupTeams.BRAZIL, scoreBoard.getMatches().get(teamPair).getAwayTeam());
    }

    @Test
    void testUpdateScore() {
        scoreBoard.startMatch(teamPair);
        scoreBoard.updateScore(teamPair, 2, 3);

        Match match = scoreBoard.getMatches().get(teamPair);
        assertEquals(2, match.getHomeScore());
        assertEquals(3, match.getAwayScore());
    }

    @Test
    void testFinishMatch() {
        scoreBoard.startMatch(teamPair);
        scoreBoard.finishMatch(teamPair);

        assertEquals(0, scoreBoard.getMatches().size());
    }

    @Test
    void testMatchSummary() {
        scoreBoard.startMatch(teamPair);
        scoreBoard.updateScore(teamPair, 1, 0);

        TeamPair pair2 = new TeamPair(WorldCupTeams.CANADA, WorldCupTeams.ITALY);
        scoreBoard.startMatch(pair2);
        scoreBoard.updateScore(pair2, 2, 1);

        List<Match> summary = scoreBoard.getSummary();
        assertEquals(2, summary.size());
        assertEquals("CANADA 2 - 1 ITALY", summary.get(0).toString());
        assertEquals("ARGENTINA 1 - 0 BRAZIL", summary.get(1).toString());
    }

    @Test
    public void testPreventMultipleMatchesForSameTeam() {
        scoreBoard.startMatch(teamPair);
        TeamPair teamPair2 = new TeamPair(WorldCupTeams.ARGENTINA, WorldCupTeams.CANADA);

        // Attempting to start a match for Team A again should throw an exception
        TeamAlreadyPlayingException thrown = assertThrows(TeamAlreadyPlayingException.class, () -> {
            scoreBoard.startMatch(teamPair2);
        });

        assertEquals("A team cannot have more than one ongoing match.", thrown.getMessage());
    }

    @Test
    public void testPreventMultipleMatchesForSameAwayTeam() {
        scoreBoard.startMatch(teamPair);
        TeamPair teamPair2 = new TeamPair(WorldCupTeams.CANADA, WorldCupTeams.BRAZIL);

        // Attempting to start a match for Team A again should throw an exception
        TeamAlreadyPlayingException thrown = assertThrows(TeamAlreadyPlayingException.class, () -> {
            scoreBoard.startMatch(teamPair2);
        });

        assertEquals("A team cannot have more than one ongoing match.", thrown.getMessage());
    }

    @Test
    public void testPreventNegativeScores() {
        scoreBoard.startMatch(teamPair);

        // Attempting to update with negative scores should throw an exception
        InvalidScoreException thrown = assertThrows(InvalidScoreException.class, () -> {
            scoreBoard.updateScore(teamPair, -1, 2);
        });

        // Verify that the exception message is as expected
        assertEquals("Scores cannot be negative.", thrown.getMessage());

        thrown = assertThrows(InvalidScoreException.class, () -> {
            scoreBoard.updateScore(teamPair, 1, -2);
        });

        // Verify that the exception message is as expected
        assertEquals("Scores cannot be negative.", thrown.getMessage());
    }

    @Test
    public void testPreventScoreReduction(){
        scoreBoard.startMatch(teamPair);
        scoreBoard.updateScore(teamPair, 2, 3);

        //Attempting to reduce score of an ongoing match should throw an exception
        InvalidScoreException thrown = assertThrows(InvalidScoreException.class, () -> {
            scoreBoard.updateScore(teamPair, 1, 3);
        });

        assertEquals("Scores of an ongoing match cannot be reduced.", thrown.getMessage());

        thrown = assertThrows(InvalidScoreException.class, () -> {
            scoreBoard.updateScore(teamPair, 2, 2);
        });

        assertEquals("Scores of an ongoing match cannot be reduced.", thrown.getMessage());
    }
}