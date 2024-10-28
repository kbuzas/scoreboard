package model;

import exceptions.InvalidScoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void testMatchInitialization(){
        Match match = new Match(WorldCupTeams.ARGENTINA, WorldCupTeams.BRAZIL);
        assertEquals(WorldCupTeams.ARGENTINA, match.getHomeTeam());
        assertEquals(WorldCupTeams.BRAZIL, match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    public void testMatchUpdateScore() {
        Match match = new Match(WorldCupTeams.ARGENTINA, WorldCupTeams.BRAZIL);
        match.updateScore(1, 1); // Initial update
        assertEquals(1, match.getHomeScore());
        assertEquals(1, match.getAwayScore());

        // Attempting to reduce score after match has been settled
        InvalidScoreException thrown = assertThrows(InvalidScoreException.class, () -> {
            match.updateScore(0, 1);
        });
        assertEquals("Scores of an ongoing match cannot be reduced.", thrown.getMessage());
    }

}