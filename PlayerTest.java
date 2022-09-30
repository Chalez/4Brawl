

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest
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
        a = new Player("Player A"); //Default stats
        b = new Player("Player B", 2, 2, 0); //+1 to damage
        c = new Player("Player C", 2, 1, 1); //+1 to dice rolls
        d = new Player("Player D", 3, 1, 0); //+1 to health
        e = new Player("Player E", 2, 1, -1); //-1 to dice rolls
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
    
    //Ensures a player starts with a current hp equal to their max hp.
    @Test
    public void startsFullHp(){
        assertEquals(a.getHp(), a.getMaxHp());
    }
    
    //Ensures players can take damage which resets their health value.
    @Test
    public void takeDamage(){
        a.takeDmg(1);
        assertEquals(a.getHp(), 1);
    }
    
    // Makes sure players can hurt other players based on their damage stat
    @Test
    public void dealDamage(){
        a.dealDmg(b);
        assertEquals(b.getHp(), 1);
    }
    
    // Makes sure healing works
    @Test
    public void heal(){
        a.takeDmg(1);
        a.heal(1);
        assertEquals(a.getHp(), 2);
    }
    
    // Ensures players cannot go below 0 hp
    @Test
    public void noOverkill(){
        a.takeDmg(3);
        assertEquals(a.getHp(), 0);
    }
    
    // Ensures players cannot heal above their max health
    @Test
    public void noOverheal(){
        a.takeDmg(1);
        a.heal(4);
        assertEquals(a.getHp(), 2);
    }
    
    // Tests that player b's higher damage stat works
    @Test
    public void higherDamageStat(){
        b.dealDmg(a);
        assertEquals(a.getHp(), 0);
    }
    
    // Ensures players cannot damage themselves
    @Test
    public void noSelfDamage(){
        a.dealDmg(a);
        assertEquals(a.getHp(), 2);
    }
    
    // Ensures players recognize when they are dead (at 0 health)
    @Test
    public void isDead(){
        a.takeDmg(3);
        assertTrue(a.isDead());
    }
    
    // Makes sure isDead has no false positives
    @Test
    public void isntDead(){
        assertTrue(!a.isDead());
    }
    
    // Ensures players who are dead cannot be healed conventionally
    @Test
    public void noRevives(){
        a.takeDmg(3);
        a.heal(4);
        assertEquals(a.getHp(), 0);
    }
    
    // Makes sure players can "reset" to their default state and hp is affected
    @Test
    public void hpReset(){
        a.takeDmg(1);
        a.reset();
        assertEquals(a.getHp(), 2);
    }
    
    // Ensures resetting CAN revive players
    @Test
    public void resetRevive(){
        a.takeDmg(3);
        a.reset();
        assertEquals(a.getHp(), 2);
    }
    
    // Makes sure players can "reset" to their default state and dmg is affected
    @Test
    public void dmgReset(){
        a.setDmg(5);
        a.reset();
        assertEquals(a.getDmg(), 1);
    }
    
    // Makes sure players can "reset" to their default state and dicemod is affected
    @Test
    public void modReset(){
        a.setMod(5);
        a.reset();
        assertEquals(a.getMod(), 0);
    }
    
    // Makes sure players can "reset" to their default state and max hp is affected
    @Test
    public void maxHpReset(){
        a.setMaxHp(5);
        a.reset();
        assertEquals(a.getMaxHp(), 2);
    }
    
    // Makes sure players can return their names
    @Test
    public void getName(){
        assertEquals(a.getName(), "Player A");
    }
    
    // Makes sure players having a number added to their rolls works
    // Statistical anomalies cannot make this method fail
    @Test
    public void posMod(){
        Runner r = new Runner();
        for (int i = 0; i < 99; i++){
            Roll q = c.roll();
            r.addResult(String.valueOf(q.getCurrent()));
        }
        assertEquals(r.getMap().get("1"), null);
    }
    
    // Makes sure players having a number subtracted from their rolls works
    // Statistical anomalies cannot make this method fail
    @Test
    public void negMod(){
        Runner r = new Runner();
        for (int i = 0; i < 99; i++){
            Roll q = e.roll();
            r.addResult(String.valueOf(q.getCurrent()));
        }
        assertEquals(r.getMap().get("6"), null);
    }
    
    // Tests that Dmg value can be set
    @Test
    public void modDmg(){
        b.setDmg(1);
        b.dealDmg(a);
        assertEquals(a.getHp(), 1);
    }
    
    // Tests that DiceMod can be set
    @Test
    public void modMod(){
        Runner r = new Runner();
        a.setMod(1);
        for (int i = 0; i < 99; i++){
            Roll q = a.roll();
            r.addResult(String.valueOf(q.getCurrent()));
        }
        assertEquals(r.getMap().get("1"), null);
    }
    
    // Tests that current hp can be set
    @Test
    public void modHp(){
        a.setHp(1);
        assertEquals(a.getHp(), 1);
    }
    
    // Tests that max hp can be set
    @Test
    public void modMaxHp(){
        a.setMaxHp(3);
        assertEquals(a.getMaxHp(), 3);
    }
}
