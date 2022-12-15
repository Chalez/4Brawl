

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests each round which calculates who damages who.
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
        riggedHighC = new Player("High C", 2, 1, 99);
        riggedHighD = new Player("High D", 2, 1, 99);
        riggedHighE = new Player("High E", 2, 1, 99);
        riggedHighF = new Player("High F", 2, 1, 99);
        
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
    
    /** One player damages another */
    @Test
    public void basicDamage(){
        Round r = new Round(new Roll[]{a, c});
        r.run();
        assertEquals(riggedOneA.getHp(), 1);
        assertEquals(riggedHighC.getHp(), 2);
    }
    
    /** Both players each other, but not themselves */
    @Test
    public void twoSameNumbersLow(){
        Round r = new Round(new Roll[]{a, b});
        r.run();
        assertEquals(riggedOneA.getHp(), 1);
        assertEquals(riggedOneB.getHp(), 1);
    }
    
    /** Both players each other, but not themselves */
    @Test
    public void twoSameNumbersHigh(){
        Round r = new Round(new Roll[]{d, c});
        r.run();
        assertEquals(riggedHighC.getHp(), 1);
        assertEquals(riggedHighD.getHp(), 1);
    }
    
    /** 3 different results- Highest damages lowest, middle does nothing */
    @Test
    public void threeDifferent(){
        Round r = new Round(new Roll[]{a, c, f});
        r.run();
        assertEquals(riggedOneA.getHp(), 1);
        assertEquals(riggedHighC.getHp(), 2);
        assertEquals(riggedHighC.getHp(), 2);
    }
    
    /** One player damages two others */
    @Test
    public void threeTieLow(){
        Round r = new Round(new Roll[]{d, c, f});
        r.run();
        assertEquals(riggedHighD.getHp(), 1);
        assertEquals(riggedHighC.getHp(), 1);
        assertEquals(riggedHighF.getHp(), 2);
    }
    
    /** Two players damage one */
    @Test
    public void threeTieHigh(){
        Round r = new Round(new Roll[]{a, c, d});
        r.run();
        assertEquals(riggedOneA.getHp(), 0);
        assertEquals(riggedHighC.getHp(), 2);
        assertEquals(riggedHighD.getHp(), 2);
    }
    
    /** 3 players all damage each other, but not themselves */
    @Test
    public void threeTieFull(){
        Round r = new Round(new Roll[]{e, c, d});
        r.run();
        assertEquals(riggedHighE.getHp(), 0);
        assertEquals(riggedHighC.getHp(), 0);
        assertEquals(riggedHighD.getHp(), 0);
    }
    
    /** Tests detection of the highest and lowest values in a round */
    @Test
    public void findHighestLowest(){
        Round r = new Round(new Roll[]{a, b, c, d, e, f});
        
        assertEquals(r.findHighest(), 6);
        assertEquals(r.findLowest(), 1);
    }
    
    /** Tests the process of finding "indices" based on a given value */
    @Test
    public void indices(){
        Round r = new Round(new Roll[]{a, b, c, d, e, f});
        
        assertEquals(r.getIndices(1).size(), 2);
        assertEquals(r.getIndices(3).size(), 3);
        assertEquals(r.getIndices(6).size(), 1);
        assertEquals(r.getIndices(0).size(), 0);
    }
}
