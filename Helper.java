
/**
 * A helper class which, currently, only contains the static method printIf.
 */
public class Helper
{
    /**
     * If the input boolean is true, the input string is printed. Otherwise, nothing happens.
     * This is used to implement the printing mode in fewer lines.
     */
    public static void printIf(boolean f, String text)
    {
        if(f){
            System.out.println(text);
        }
    }
}
