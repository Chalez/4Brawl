
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    // A player's name, which is returned when they win a brawl
    private String name;
    

    /**
     * Constructor for objects of class Player
     */
    public Player(String s, int h, int d, int m)
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
    }
    
    public Player()
    {
        name = "Default";
        hp = 2;
        maxHp = 2;
        dmg = 1;
        diceMod = 0;
    }

    public int getMaxHp(){
        return maxHp;
    }
    
    public int getHp(){
        return hp;
    }
    
    public int getDmg(){
        return dmg;
    }
    
    public void takeDmg(int dmgFromPlayer){
        if (dmgFromPlayer > 0){
            hp -= dmgFromPlayer;
        }
        lowerBoundHp();
    }
    
    public void dealDmg(Player p){
        p.takeDmg(this.dmg);
    }
    
    public void upperBoundHp(){
        if(hp > maxHp){
            hp = maxHp;
        }
    }
    
    public void lowerBoundHp(){
        if(hp < 0){
            hp = 0;
        }
    }
    
    public boolean isDead(){
        return (hp == 0);
    }
    
    public void reset(){
        hp = defHp;
        maxHp = defHp;
        dmg = defDmg;
        diceMod = defMod;
    }
    
    public void heal(int h){
        if (h > 0){
            hp += h;
        }
        upperBoundHp();
    }
    
    public String getName(){
        return name;
    }
    
    // Lets a player roll a dice
    public Roll roll(int maximum){
         Roll r = new Roll(this, maximum);
         r.modifyCurrent(diceMod);
         return r;
    }
    
    public Roll roll(){
        return new Roll(this);
    }
}
