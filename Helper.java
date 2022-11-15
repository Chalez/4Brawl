
/**
 * Write a description of class Helper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Helper
{
    private int x;

    /**
     * Constructor for objects of class Helper
     */
    public Helper()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void printIf(boolean f, String text)
    {
        if(f){
            System.out.println(text);
        }
    }
}
