

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BrawlTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
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
    
    @Test
    public void resetHP(){
        a.takeDmg(1);
        b.takeDmg(2);
        
        Brawl q = new Brawl(new Player[]{a, b});
        q.resetPlayers();
        
        assertEquals(a.getHp(), 2);
        assertEquals(b.getHp(), 2);
    }
}
