

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AttributeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AttributeTest
{
    Player a;
    Player b;
    Player c;
    Player d;
    Player e;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        a = new Player("Player A", 2, 1, 0, new String[]{"example", "apple", "apple"});
        b = new Player("Player B", 2, 1, 9, new String[]{"timedDmgDown"});
        c = new Player("Player C", 5, 1, -9, new String[]{});
        d = new Player("Player B", 2, 1, 9, new String[]{"timedDmgDown", "timedDmgDown"});
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        a.reset();
        b.reset();
        c.reset();
        d.reset();
    }
    
    @Test
    public void attributeNumber0(){
        assertEquals(a.getAttribute("asubdsavtdiuv"), -1);
    }
    
    @Test
    public void attributeNumber1(){
        assertEquals(a.getAttribute("example"), 1);
    }
    
    @Test
    public void attributeNumber2(){
        assertEquals(a.getAttribute("apple"), 2);
    }
    
    @Test
    public void attributeTrue(){
        assertTrue(a.hasAttribute("example"));
    }
    
    @Test
    public void attributeFalse(){
        assertTrue(!(a.hasAttribute("q")));
    }
    
    // Tests that, after 1 roll, player b's dmg stat is reduced by 1 (to 0)
    @Test
    public void dmgDown1(){
        Round r = new Round(new Roll[]{b.roll(), c.roll()});
        r.run();
        assertEquals(c.getHp(), 4);
        r = new Round(new Roll[]{b.roll(), c.roll()});
        r.run();
        assertEquals(c.getHp(), 4);
    }
    
    @Test
    public void dmgDown2(){
        Round r = new Round(new Roll[]{d.roll(), c.roll()});
        r.run();
        assertEquals(c.getHp(), 4);
        r = new Round(new Roll[]{d.roll(), c.roll()});
        r.run();
        assertEquals(c.getHp(), 3);
        r = new Round(new Roll[]{d.roll(), c.roll()});
        r.run();
        assertEquals(c.getHp(), 3);
    }
}
