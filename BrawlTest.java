

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the brawls which put players through repeated rounds.
 */
public class BrawlTest
{
    Player a;
    Player b;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        a = new Player("Player A");
        b = new Player("Player B");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /** Tests that a brawl can reset all of its players' health */
    @Test
    public void resetHP(){
        a.takeDmg(1);
        b.takeDmg(2);
        
        Brawl q = new Brawl(new Player[]{a, b});
        q.resetPlayers();
        
        assertEquals(a.getHp(), 2);
        assertEquals(b.getHp(), 2);
    }
    
    /** Tests that a brawl can reset all of its players' dmg */
    @Test
    public void resetDmg(){
        a.setDmg(3);
        b.setDmg(3);
        
        Brawl q = new Brawl(new Player[]{a, b});
        q.resetPlayers();
        
        assertEquals(a.getDmg(), 1);
        assertEquals(b.getDmg(), 1);
    }
    
    /** Tests that a brawl can reset all of its players' dice modifier */
    @Test
    public void resetMod(){
        a.setMod(2);
        a.setMod(2);
        
        Brawl q = new Brawl(new Player[]{a, b});
        q.resetPlayers();
        
        assertEquals(a.getMod(), 0);
        assertEquals(b.getMod(), 0);
    }
    
    /** Finds the number of players alive in the brawl */
    @Test
    public void livingCount(){
        Brawl q = new Brawl(new Player[]{a, b});
        assertEquals(q.livingPlayers(), 2);
        a.takeDmg(2);
        assertEquals(q.livingPlayers(), 1);
        b.takeDmg(2);
        assertEquals(q.livingPlayers(), 0);
    }
    
    /** Finds the last living player in the brawl */
    @Test
    public void lastPlayer(){
        Brawl q = new Brawl(new Player[]{a, b});
        a.takeDmg(2);
        assertEquals(q.lastPlayer(), b);
    }
}
