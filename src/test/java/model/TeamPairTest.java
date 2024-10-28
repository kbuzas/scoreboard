package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamPairTest {

    @Test
    void testTeamPairEquality() {
        TeamPair pair1 = new TeamPair(WorldCupTeams.ARGENTINA, WorldCupTeams.BRAZIL);
        TeamPair pair2 = new TeamPair(WorldCupTeams.ARGENTINA, WorldCupTeams.BRAZIL);
        TeamPair pair3 = new TeamPair(WorldCupTeams.CANADA, WorldCupTeams.GERMANY);

        assertEquals(pair1, pair2);
        assertNotEquals(pair1, pair3);
    }

    @Test
    void testTeamPairHashCode() {
        TeamPair pair1 = new TeamPair(WorldCupTeams.ARGENTINA, WorldCupTeams.BRAZIL);
        TeamPair pair2 = new TeamPair(WorldCupTeams.ARGENTINA, WorldCupTeams.BRAZIL);

        assertEquals(pair1.hashCode(), pair2.hashCode());
    }

}