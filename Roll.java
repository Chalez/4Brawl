import java.util.Random;

/**
 * A sigular roll of a dice, which is owned by a player.
 * This isn't a dice which can be rolled muliple times- once it is used, it is discarded.
 */
public class Roll
{
    /** The highest possible value of the roll, or its "size". */
    private int max;
    /** The current value stored by the roll. */
    private int current;
    /** The player who owns the roll. */
    private Player owner;

    /** Full Constructor, with an owner and a max dice size. */
    public Roll(Player n, int maximum)
    {
        if (maximum < 1){
            max = 1;
        }
        else{
            max = maximum;
        }
        roll(); 
        owner = n;
    }
    
    /** Constructor with just a maximum size defined. */
    public Roll(int maximum)
    {
        this(new Player("default"), maximum);
    }
    
    /** Constructor with just an owner defined. */
    public Roll(Player n)
    {
        this(n, 6);
    }
    
    /** Default constructor with a default owner and a default size of 6. */
    public Roll()
    {
        this(new Player("default"), 6);
    }
    
    /** Generates a random number stored in the roll. Currently only called on creation of the roll, but could also be used for reroll effects. */
    public void roll()
    {
        current = (int) (Math.random() * this.max + 1);
    }

    /** Returns the current value of the roll. */
    public int getCurrent()
    {
        return current;
    }
    
    /** Sets the roll's value to a given integer. */
    public void setCurrent(int x)
    {
        current = x;
        upperBound();
        lowerBound();
    }
    
    /** Sets the roll's value to its maximum size if it is higher. */
    public void upperBound(){
        if(current > max){
            current = max;
        }
    }
    
    /** Sets the roll's value to 1 if it is lower. */
    public void lowerBound(){
        if(current < 1){
            current = 1;
        }
    }
    
    /** Adds to (or subtracts from, if negative) the roll's current value. */
    public void modifyCurrent(int modifier){
        current += modifier;
        lowerBound();
        upperBound();
    }
    
    /** Returns the player who owns the roll. */
    public Player getOwner(){
        return owner;
    }
    
    /** Returns the roll's current value as a string. */
    public String toString(){
        return "" + current;
    }
}
