package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamPairTest {

    @Test
    void testTeamPairEquality() {
        TeamPair pair1 = new TeamPair("Team A", "Team B");
        TeamPair pair2 = new TeamPair("Team A", "Team B");
        TeamPair pair3 = new TeamPair("Team C", "Team D");

        assertEquals(pair1, pair2);
        assertNotEquals(pair1, pair3);
    }

    @Test
    void testTeamPairHashCode() {
        TeamPair pair1 = new TeamPair("Team A", "Team B");
        TeamPair pair2 = new TeamPair("Team A", "Team B");

        assertEquals(pair1.hashCode(), pair2.hashCode());
    }

}