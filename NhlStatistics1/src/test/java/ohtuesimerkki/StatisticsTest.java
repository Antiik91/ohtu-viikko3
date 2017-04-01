/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author janantik
 */
public class StatisticsTest {
    Reader readerStub = new Reader(){
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45,54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            
            return players;
        }
        
    };
    Statistics testStats;

    @Before
    public void setUp() {
        testStats = new Statistics(readerStub);
    }
    
    @Test
    public void searchReturnsCorrectPlayer(){
        String lemi = "Lemieux";
        Player lemiTest = testStats.search("Lemieux");
        assertEquals(lemi, lemiTest.getName());
    }
    
    @Test
    public void searchReturnsNull() {
        assertNull(testStats.search("LemiÖÖ"));
    }
    
    @Test
    public void testTeam() {
        List<Player> edmontonPlayers = testStats.team("EDM");
        assertEquals(3, edmontonPlayers.size());
    }
    @Test
    public void testTopScorers() {
        List<Player> twoBest = testStats.topScorers(2);
        int correctPlayers = 0;
        for (Player player : twoBest) {
            if (player.getName().equals("Lemieux") || player.getName().equals("Yzerman")) {
                correctPlayers++;
            }
        }
        assertTrue(correctPlayers == 2);
    }
}
