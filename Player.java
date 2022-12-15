import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A Player has multiple stats which determine its performance in brawls, and a name which is returned if they win.
 * Players can roll dice, take damage, and damage other players.
 */
public class Player
{
    // The highest a player's HP can be and its current value, respectively.
    private int maxHp;
    private int hp;
    
    // How much damage a player deals.
    private int dmg;
    
    // The base modifier to all of the player's rolls.
    private int diceMod;
    
    // Default values for the player, what they are reset to should they be changed in a brawl.
    private int defHp;
    private int defDmg;
    private int defMod;
    
    // The size of dice the player rolls.
    // Since this is changed so little, doing so is limited to Attributes.
    private int diceSize;
    
    // Ticks up every time the player rolls, for timed attributes.
    private int rollTimer;
    
    // A player's name, which is returned when they win a brawl.
    private String name;
    
    // The Runner which stores Attributes, additional modifiers.
    private Runner runner;
    private HashMap attributes;
    
    // The number of times a player can add 1 to their rolls. Used by multiAddOne attribute
    private int plusOnes;
    private int defPlusOnes;
    
    /** Full Constructor, with a name, stats, and attributes. */
    public Player(String s, int h, int d, int m, String[] atts)
    {
        name = s;
        if(h < 1){
            defHp = 1;
        }
        else{
            defHp = h;
        }
        hp = defHp;
        maxHp = defHp;
        if(d < 0){
            defDmg = 0;
        }
        else{
            defDmg = d;
        }
        dmg = d;
        defMod = m;
        diceMod = m;
        
        diceSize = 6;
        
        rollTimer = -1;
        
        runner = new Runner();
        for(int i = 0; i < atts.length; i++){
            runner.addResult(atts[i]);
        }
        attributes = runner.getMap();
        
        if(hasAttribute("timedD8") || hasAttribute("D8")){
            diceSize = 8;
        }
        
        defPlusOnes = getAttribute("multiAddOne");
        plusOnes = defPlusOnes;
    }

    /** Constructor, with a name and stats. */
    public Player(String s, int h, int d, int m)
    {
        this(s, h, d, m, new String[0]);
    }
    
    /** Constructor with just a name. */
    public Player(String s)
    {
        this(s, 2, 1, 0);
    }
    
    /** Default constructor with a default name and stats. */
    public Player()
    {
        this("Default", 2, 1, 0);
    }
    
    /** Returns the player's maximum hp. */
    public int getMaxHp(){
        return maxHp;
    }
    
    /** Returns the player's current hp. */
    public int getHp(){
        return hp;
    }
    
    /** Returns the player's current dmg. */
    public int getDmg(){
        return dmg;
    }
    
    /** Returns the player's current dice modifier. */
    public int getMod(){
        return diceMod;
    }
    
    /** Changes a player's max hp to a nonnegative value. Currently not used, but effects which do so exist. */
    public void setMaxHp(int a){
        if(a >= 0){
            maxHp = a;
        }
        upperBoundHp();
        lowerBoundHp();
    }
    
    /** Sets the player's current hp to a nonnegative value. */
    public void setHp(int a){
        if(a >= 0){
            hp = a;
        }
        upperBoundHp();
        lowerBoundHp();
    }
    
    /** Sets the player's current dmg to a nonnegative value. */
    public void setDmg(int a){
        if(a >= 0){
            dmg = a;
        }
    }
    
    /** Sets the player's current dice mod. */
    public void setMod(int a){
        diceMod = a;
    }
    
    /** Causes a player to take a nonzero amount of damage, and if d is true, prints if the player dies. */
    public void takeDmg(int dmgFromPlayer, boolean d){
        boolean startingLife = this.isDead();
        if (dmgFromPlayer > 0){
            hp -= dmgFromPlayer;
        }
        lowerBoundHp();
        
        if(this.isDead() && !startingLife){
            Helper.printIf(d, getName() + " Dies.");
        }
    }
    
    /** Causes the player to take a nonzero amount of damage. */
    public void takeDmg(int dmgFromPlayer){
        takeDmg(dmgFromPlayer, false);
    }
    
    /** Deals damage to another player equal to dmg, and prints it if d is true.
        Notably, this does not allow a player to damage themselves.*/
    public void dealDmg(Player p, boolean d){
        if(p != this){
            p.takeDmg(this.dmg, d);
        }
    }
    
    /** Deals damage to another player equal to dmg without printing. */
    public void dealDmg(Player p){
        dealDmg(p, false);
    }
    
    /** Sets the player's current hp to the maximum if it is higher. */
    public void upperBoundHp(){
        if(hp > maxHp){
            hp = maxHp;
        }
    }
    
    /** Sets the player's current hp to 0 if it is lower. */
    public void lowerBoundHp(){
        if(hp < 0){
            hp = 0;
        }
    }
    
    /** Returns whether the player is dead (at 0hp). */
    public boolean isDead(){
        return (hp == 0);
    }
    
    /** Resets all of the player's stats to their default values. Called between brawls to refresh attributes and health.*/
    public void reset(){
        hp = defHp;
        maxHp = defHp;
        dmg = defDmg;
        diceMod = defMod;
        rollTimer = -1;
        plusOnes = defPlusOnes;
    }
    
    /** Heals the player by a nonnegative amount. Currently not used, but effects which do so exist. */
    public void heal(int h){
        if (h > 0 && !this.isDead()){
            hp += h;
        }
        upperBoundHp();
    }
    
    /** Returns the name of the player as a string. */
    public String getName(){
        return name;
    }
    
    /** Returns a new roll owned by the player, with a size based on input. */
    public Roll roll(int maximum){
        rollTimer++;
        // Because damage is tied to the player not the roll, but dice size is roll based...
        // All timed mods happen before the roll.
        
        if(rollTimer == getAttribute("timedDmgDown")){
             dmg--;
         } 
        if(rollTimer == getAttribute("timedModDown")){
             diceMod--;
        } 
        if(rollTimer == getAttribute("timedD8")){
             diceSize--;
             diceSize--;
        } 
        
        Roll r = new Roll(this, maximum);
        r.modifyCurrent(diceMod);
        
         return r;
    }
    
    /** Returns a new roll owned by the player, with a size based on the player's attributes. */
    public Roll roll(){
        return roll(diceSize);
    }
    
    /** Returns true if the player has the given attribute, returns false otherwise. */
    public boolean hasAttribute(String s){
        if(((AtomicInteger)attributes.getOrDefault(s, new AtomicInteger(0))).intValue() == 0){
            return false;
        }
        return true;
    }
    
    /** Returns the value of the given attribute, or -1 if the attribute is not present. */
    public int getAttribute(String s){
        AtomicInteger x = (AtomicInteger)attributes.getOrDefault(s, new AtomicInteger(-1));
        return x.intValue();
    }
    
    /** Performs reactions to a set of rolls given an array of rolls and the index of the one belonging to the player.
     *  Currently used for the multiAddOne Attribute, but more could be included in the future.
       */
    public void respond(int ownedIndex, Roll[] allRolls){
        int lowestOther = 99;
        for(int i = 0; i < allRolls.length; i++){
            if(i != ownedIndex && allRolls[i].getCurrent() < lowestOther){
                lowestOther = allRolls[i].getCurrent();
            }
        }
        int rollsDif = lowestOther - allRolls[ownedIndex].getCurrent();
        if( rollsDif > 0 && rollsDif <= plusOnes){
            plusOnes -= rollsDif;
            allRolls[ownedIndex].modifyCurrent(rollsDif);
            //System.out.println("Added " + rollsDif);
            rollsDif = lowestOther - allRolls[ownedIndex].getCurrent();
        }
        if( rollsDif == 0 && plusOnes > 0){
            plusOnes--;
            //System.out.println("Added 1");
            allRolls[ownedIndex].modifyCurrent(1);
        }
    }
}
