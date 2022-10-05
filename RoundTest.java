

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoundTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RoundTest
{
    Player riggedOneA;
    Player riggedOneB;
    Player riggedHighE;
    Player riggedHighF;
    Player riggedHighC;
    Player riggedHighD;
    
    Roll a;
    Roll b;
    Roll c;
    Roll d;
    Roll e;
    Roll f;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        // These players have -99 to rolls, so will always roll a 1.
        riggedOneA = new Player("One A", 2, 1, -99);
        riggedOneB = new Player("One B", 2, 1, -99);
        // These players have +99 to rolls, so will always roll the max value of the dice.
        riggedHighC = new Player("High A", 2, 1, 99);
        riggedHighD = new Player("High B", 2, 1, 99);
        riggedHighE = new Player("High C", 2, 1, 99);
        riggedHighF = new Player("High D", 2, 1, 99);
        
        a = riggedOneA.roll(1);
        b = riggedOneB.roll(1);
        c = riggedHighC.roll(3);
        d = riggedHighD.roll(3);
        e = riggedHighE.roll(3);
        f = riggedHighF.roll(6);
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
    public void startsFullHp(){
        Round r = new Round(new Roll[]{a, c});
        r.run();
        assertEquals(riggedOneA.getHp(), 1);
        assertEquals(riggedHighC.getHp(), 2);
    }
}
