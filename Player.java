import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A Player has multiple stats which determine its performance in brawls...
 * and a name which is returned if they win.
 * Players can roll dice, take damage, and damage other players.
 */
public class Player
{
    // The highest a player's HP can be and its current value, respectively.
    private int maxHp;
    private int hp;
    
    // Home much damage a player deals.
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
    
    // Ticks up every time the player rolls
    private int rollTimer;
    
    // A player's name, which is returned when they win a brawl
    private String name;
    
    // The runner which stores Attributes, additional modifiers.
    private Runner runner;
    private HashMap attributes;
    
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
    }

    // Constructor with Name, Health, Damage, and Modifier
    public Player(String s, int h, int d, int m)
    {
        this(s, h, d, m, new String[0]);
    }
    
    // Default stats with only a Name
    public Player(String s)
    {
        this(s, 2, 1, 0);
    }
    
    // Default Constructor
    public Player()
    {
        this("Default", 2, 1, 0);
    }
    
    // Returns the player's max HP
    public int getMaxHp(){
        return maxHp;
    }
    
    // Returns the player's current HP
    public int getHp(){
        return hp;
    }
    
    //Returns the player's DMG stat
    public int getDmg(){
        return dmg;
    }
    
    // Returns the player's dice modifier
    public int getMod(){
        return diceMod;
    }
    
    // Sets the player's max HP, should it change from an effect
    public void setMaxHp(int a){
        if(a >= 0){
            maxHp = a;
        }
        upperBoundHp();
        lowerBoundHp();
    }
    
    // Sets the player's current HP
    public void setHp(int a){
        if(a >= 0){
            hp = a;
        }
        upperBoundHp();
        lowerBoundHp();
    }
    
    // Sets a player's current DMG
    public void setDmg(int a){
        if(a >= 0){
            dmg = a;
        }
    }
    
    // Sets the player's current dice modifier
    public void setMod(int a){
        diceMod = a;
    }
    
    // Takes a positive nonzero amount of damage
    public void takeDmg(int dmgFromPlayer){
        if (dmgFromPlayer > 0){
            hp -= dmgFromPlayer;
        }
        lowerBoundHp();
    }
    
    // Deals damage to another player, causing them to take damage equal to your stat.
    public void dealDmg(Player p){
        if(p != this){
            p.takeDmg(this.dmg);
        }
    }
    
    // Makes sure your HP isn't too high
    public void upperBoundHp(){
        if(hp > maxHp){
            hp = maxHp;
        }
    }
    
    // Makes sure your HP isn't too low
    public void lowerBoundHp(){
        if(hp < 0){
            hp = 0;
        }
    }
    
    // Returns whether the player is dead (At 0 HP)
    public boolean isDead(){
        return (hp == 0);
    }
    
    // Sets a player's stats back to the default
    public void reset(){
        hp = defHp;
        maxHp = defHp;
        dmg = defDmg;
        diceMod = defMod;
        rollTimer = -1;
    }
    
    // Heals the player by an amount
    public void heal(int h){
        if (h > 0 && !this.isDead()){
            hp += h;
        }
        upperBoundHp();
    }
    
    // Returns the player's name
    public String getName(){
        return name;
    }
    
    // Lets a player roll a dice
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
    
    // Default for the roll method
    public Roll roll(){
        return roll(diceSize);
    }
    
    public boolean hasAttribute(String s){
        if(((AtomicInteger)attributes.getOrDefault(s, new AtomicInteger(0))).intValue() == 0){
            return false;
        }
        return true;
    }
    
    public int getAttribute(String s){
        AtomicInteger x = (AtomicInteger)attributes.getOrDefault(s, new AtomicInteger(-1));
        return x.intValue();
    }
}
