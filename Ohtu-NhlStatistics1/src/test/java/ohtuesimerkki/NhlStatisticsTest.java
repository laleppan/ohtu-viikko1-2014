/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtuesimerkki;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author lasse
 */
public class NhlStatisticsTest {
    
    public NhlStatisticsTest() {
    }
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //Statistics stats = new Statistics(StatisticsTest);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    public class StaticsticsTest {

        Statistics stats;
        Reader readerStub = new Reader() {

            public List<Player> getPlayers() {
                ArrayList<Player> players = new ArrayList<Player>();

                players.add(new Player("Semenko", "EDM", 4, 12));
                players.add(new Player("Lemieux", "PIT", 45, 54));
                players.add(new Player("Kurri", "EDM", 37, 53));
                players.add(new Player("Yzerman", "DET", 42, 56));
                players.add(new Player("Gretzky", "EDM", 35, 89));

                return players;
            }
        };

        public StaticsticsTest() {
            this.stats = new Statistics(readerStub);
        }

    }
    
    @Test
    public void testSearch() {
        StaticsticsTest test = new StaticsticsTest();
        Player searched = test.stats.search("Semenko");
        assertEquals("Semenko", searched.getName());
        Player searched2 = test.stats.search("kukkuu");
        assertNull(searched2);   
    }
  
    @Test
    public void testTeam() {
        StaticsticsTest test = new StaticsticsTest();
        List<Player> players = test.stats.team("EDM");
        List<String> expected = new ArrayList();
       
        expected.add("Semenko");
        expected.add("Kurri");
        expected.add("Gretzky");
        for (int counter = 0; counter < players.size(); counter++) {
            assertEquals(expected.get(counter), players.get(counter).getName());
        }
    }
    
    @Test
    public void testTopScorers() {
        StaticsticsTest test = new StaticsticsTest();
        List<Player> players = test.stats.topScorers(3);
        assertEquals(3, players.size());
        
        List<String> expected = new ArrayList();
       
        expected.add("Gretzky");
        expected.add("Lemieux");
        expected.add("Yzerman");
        for (int counter = 0; counter < players.size(); counter++) {
            assertEquals(expected.get(counter), players.get(counter).getName());
        }
    }

}
    
    

