
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

    /**
     * Constructor for objects of class Roll
     */
    public Roll(int maximum)
    {
        // initialise instance variables
        max = maximum; 
        current = roll(); 
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
    
    public void lowerBound(){
        if(current > max){
            current = max;
        }
    }
    
    public void upperBound(){
        if(current < 1){
            current = 1;
        }
    }
    
    public void modifyCurrent(int modifier){
        current += modifier;
        lowerBound();
        upperBound();
    }
    
    
    
}
