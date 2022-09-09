
import java.util.Random;


/**
 * Used to roll values
 *
 * @author Charles H
 * @author Aidan Smyth
 * @version (a version number or a date)
 */
public class Die
{
    // instance variables - replace the example below with your own
    private int max;

    /**
     * Constructor for objects of class Die
     */
    public Die(int maximum)
    {
        // initialise instance variables
        max = maximum;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int roll()
    {
        return (int) (Math.random() * this.max + 1);
    }
    
    public static void main(){
        Die d = new Die(6);
        
        System.out.println(d.roll());
        System.out.println(d.roll());
        System.out.println(d.roll());
        System.out.println(d.roll());
        System.out.println(d.roll());
        System.out.println(d.roll());
    }
    
    
}
