
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private int maxHp;
    private int hp;
    
    private int dmg;
    
    private int diceMod;
    

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        hp = 2;
        maxHp = 2;
        dmg = 1;
        diceMod = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
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
        hp = maxHp;
    }
    
    public void heal(int h){
        if (h > 0){
            hp += h;
        }
        upperBoundHp();
    }
    
}
