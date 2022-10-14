
import java.util.Random;

/**
 * A sigular roll of a dice, which is owned by a player.
 * This isn't a dice which can be rolled muliple times- once it is used, it is discarded.
 */
public class Roll
{
    // The highest possible value of the roll, or its "size".
    private int max;
    // The current value of the roll
    private int current;
    // The player who owns the roll
    private Player owner;

    // Full Constructor
    public Roll(Player n, int maximum)
    {
        // initialise instance variables
        if (maximum < 1){
            max = 1;
        }
        else{
            max = maximum;
        }
        roll(); 
        owner = n;
    }
    
    // Constructor with only maximum
    public Roll(int maximum)
    {
        // initialise instance variables
        this(null, maximum);
    }
    
    // Constructor with only owner
    public Roll(Player n)
    {
        // initialise instance variables
        this(n, 6);
    }
    
    // Default constructor
    public Roll()
    {
        // initialise instance variables
        this(null, 6);
    }
    
    // Generates a random number for the roll.
    // Only called when made or for "reroll" effects
    public void roll()
    {
        current = (int) (Math.random() * this.max + 1);
    }

    // Returns the roll's value
    public int getCurrent()
    {
        return current;
    }
    
    // Sets the roll's current value
    public void setCurrent(int x)
    {
        current = x;
        upperBound();
        lowerBound();
    }
    
    // Sets the value to the maximum value if it would be higher
    public void upperBound(){
        if(current > max){
            current = max;
        }
    }
    
    // Sets the value to 1 if it would be lower
    public void lowerBound(){
        if(current < 1){
            current = 1;
        }
    }
    
    // Adds or subtracts from the current value
    public void modifyCurrent(int modifier){
        current += modifier;
        lowerBound();
        upperBound();
    }
    
    // Returns the player who owns the roll
    public Player getOwner(){
        return owner;
    }
    
}
