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
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        a = new Roll();
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
    
    @Test
    public void worksAtAll(){
        a.setCurrent(1);
        assertEquals(a.getCurrent(), 1);
    }
    
    @Test
    public void upperBound(){
        a.setCurrent(6);
        a.modifyCurrent(1);
        assertEquals(a.getCurrent(), 6);
    }
    
    @Test
    public void lowerBound(){
        a.setCurrent(1);
        a.modifyCurrent(-1);
        assertEquals(a.getCurrent(), 1);
    }
    
    // @Test
    // public void testSomething(){
        // y == f(x)
        // expected = something
        // assertTrue(y.equals(expected))
        // assertEquals(expected, y)
    // }
}
