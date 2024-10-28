package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void testMatchInitialization(){
        Match match = new Match("Team A", "Team B");
        assertEquals("Team A", match.getHomeTeam());
        assertEquals("Team B", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

}