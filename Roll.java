
import java.util.Random;

/**
 * Write a description of class Roll here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Roll
{
    // instance variables - replace the example below with your own
    private int max;
    private int current;
    private Player owner;

    /**
     * Constructor for objects of class Roll
     */
    public Roll(Player n, int maximum)
    {
        // initialise instance variables
        if (maximum < 1){
            max = 1;
        }
        else{
            max = maximum;
        }
        current = roll(); 
        owner = n;
    }
    
    public Roll(int maximum)
    {
        // initialise instance variables
        this(null, maximum);
    }
    
    public Roll(Player n)
    {
        // initialise instance variables
        this(n, 6);
    }
    
    public Roll()
    {
        // initialise instance variables
        this(null, 6);
    }
    
    public int roll()
    {
        return (int) (Math.random() * this.max + 1);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getCurrent()
    {
        return current;
    }
    
    public void setCurrent(int x)
    {
        current = x;
    }
    
    public void upperBound(){
        if(current > max){
            current = max;
        }
    }
    
    public void lowerBound(){
        if(current < 1){
            current = 1;
        }
    }
    
    public void modifyCurrent(int modifier){
        current += modifier;
        lowerBound();
        upperBound();
    }
    
    public Player getOwner(){
        return owner;
    }
    
}
