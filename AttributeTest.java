import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the attributes players can possess.
 */
public class AttributeTest
{
    Player a;
    Player b;
    Player c;
    Player d;
    Player e;
    Player f;
    
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
        d = new Player("Player D", 2, 1, 9, new String[]{"timedDmgDown", "timedDmgDown"});
        e = new Player("Player E", 2, 1, 9, new String[]{"tiebreak"});
        f = new Player("Player F", 3, 1, 9, new String[]{});
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
        e.reset();
        f.reset();
    }
    
    /** Looks for a nonexistant attribute, and gets -1 in return */
    @Test
    public void attributeNumber0(){
        assertEquals(a.getAttribute("asubdsavtdiuv"), -1);
    }
    
    /** Looks for an attribute that there is 1 of */
    @Test
    public void attributeNumber1(){
        assertEquals(a.getAttribute("example"), 1);
    }
    
    /** Looks for an attribute that there is 2 of */
    @Test
    public void attributeNumber2(){
        assertEquals(a.getAttribute("apple"), 2);
    }
    
    /** Looks for an attribute that exists, and returns true because of it */
    @Test
    public void attributeTrue(){
        assertTrue(a.hasAttribute("example"));
    }
    
    /** Looks for an attribute that doesn't exist, and returns false because of it */
    @Test
    public void attributeFalse(){
        assertTrue(!(a.hasAttribute("q")));
    }
    
    /** Tests that, after 1 roll, player b's dmg stat is reduced by 1 (to 0) */
    @Test
    public void dmgDown1(){
        Round r = new Round(new Roll[]{b.roll(), c.roll()});
        r.run();
        assertEquals(c.getHp(), 4);
        r = new Round(new Roll[]{b.roll(), c.roll()});
        r.run();
        assertEquals(c.getHp(), 4);
    }
    
    /** Tests that, after 2 rolls, player d's dmg stat is reduced by 1 (to 0) */
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
    
    /** Tests that a tiebreak player's roll is considered higher than other rolls of the same value */
    @Test
    public void tiebreak(){
        Round r = new Round(new Roll[]{d.roll(), e.roll(), f.roll()});
        r.run();
        assertEquals(d.getHp(), 1);
        assertEquals(e.getHp(), 2);
        assertEquals(f.getHp(), 2);
    }
    
    
}
