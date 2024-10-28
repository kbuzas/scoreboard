package model;

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

}