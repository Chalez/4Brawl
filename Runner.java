
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Write a description of class Runner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Runner
{
    // The string is an input, the Integer is the number of times it's been added.
    HashMap<String, AtomicInteger> map;
    // the number of inputs given to the Runner
    int total;

    // Constructor
    public Runner()
    {
        map = new HashMap<String, AtomicInteger>();
        total = 0;
    }

    // Adds an outcome to the map
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
    
    // Prints the outcomes, the number of times they happened, and decimal equivalent.
    public void print(){
        String format = "%-20s%-20s%s%n";
        Object[] array = map.keySet().toArray();
        for(int i = 0; i < array.length; i++){
            System.out.printf(format, String.valueOf(array[i]), map.get(array[i]).toString(), String.valueOf((double)map.get(array[i]).intValue() / total));
        }
    }
    
    // Returns the map itself- mostly for the purpose of tests, which can't understand the print version
    public HashMap getMap(){
        return map;
    }
    
    // Returns the number of inputs given to this runner.
    public int getTotal(){
        return total;
    }
}
