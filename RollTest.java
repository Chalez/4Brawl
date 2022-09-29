import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RollTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RollTest
{
    Roll a;
    Player p;
    Roll b;
    Roll c;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        p = new Player();
        a = p.roll();
        b = p.roll(8);
        c = p.roll(-1);
        //a.getCurrent();
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
    
    // Setting a roll's value should make it that value. Very basic
    @Test
    public void worksAtAll(){
        a.setCurrent(1);
        assertEquals(a.getCurrent(), 1);
    }
    
    // Roll cannot exceed its highest value
    @Test
    public void upperBound(){
        a.setCurrent(6);
        a.modifyCurrent(1);
        assertEquals(a.getCurrent(), 6);
    }
    
    // Roll cannot become lower than 1
    @Test
    public void lowerBound(){
        a.setCurrent(1);
        a.modifyCurrent(-1);
        assertEquals(a.getCurrent(), 1);
    }
    
    // The player who owns a roll can be properly referenced,
    @Test
    public void referenceOwner(){
        assertEquals(a.getOwner().getName(), "Default");
    }
    
    // A dice of non-standard size can have a maximum above 6
    @Test
    public void diceSize(){
        b.setCurrent(8);
        assertEquals(b.getCurrent(), 8);
    }
    
    // The size of a dice cannot go below 1
    @Test
    public void minDiceSize(){
        c.setCurrent(-1);
        assertEquals(c.getCurrent(), 1);
    }
    
    // @Test
    // public void testSomething(){
        // y == f(x)
        // expected = something
        // assertTrue(y.equals(expected))
        // assertEquals(expected, y)
    // }
}
