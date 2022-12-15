
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * An object which takes in strings, using a hashmap to store them and how many of each have been added.
 * Used both to track the result of many brawls and to store Attributes in each player
 */
public class Runner
{
    // The string is an input, the Integer is the number of times it's been added.
    HashMap<String, AtomicInteger> map;
    // the number of inputs given to the Runner
    int total;

    /** Constructor, starting empty by default. */
    public Runner()
    {
        map = new HashMap<String, AtomicInteger>();
        total = 0;
    }

    /** Adds a string to the Runner. */
    public void addResult(String s)
    {
        total++;
        if(map.containsKey(s)){
            map.get(s).incrementAndGet();
        }
        else{
            map.put(s, new AtomicInteger(1));
        }
    }
    
    /** Prints the outcomes, the number of times they happened, and decimal equivalent. */
    public void print(){
        String format = "%-20s%-20s%s%n";
        Object[] array = map.keySet().toArray();
        for(int i = 0; i < array.length; i++){
            System.out.printf(format, String.valueOf(array[i]), map.get(array[i]).toString(), String.valueOf((double)map.get(array[i]).intValue() / total));
        }
    }
    
    /** Returns the map stored in the runner. */
    public HashMap getMap(){
        return map;
    }
    
    /** Returns the number of inputs given to this runner. */
    public int getTotal(){
        return total;
    }
}
