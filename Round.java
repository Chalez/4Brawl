
/**
 * A Round is a piece of a brawl where every player rolls a dice.
 * Players who roll the highest damage players who roll the lowest.
 */
import java.util.ArrayList;

public class Round
{
    Roll[] playerRolls;
    boolean desc;
    
    // Constructor
    public Round(Roll[] rolls, boolean d){
        playerRolls = rolls;
        desc = d;
    }
    
    public Round(Roll[] rolls){
        this(rolls, false);
    }
    
    // Runs the round, causing all highest rolls to damage lowest rolls.
    void run(){
        // Creates arraylists to store the highest and lowest rolls
        ArrayList<Integer> highest = new ArrayList<Integer>();
        ArrayList<Integer> lowest = new ArrayList<Integer>();
        
        // Puts the highest and lowest rolls into the arraylists
        highest = getIndices(findHighest());
        lowest = getIndices(findLowest());
        
        // All the highest damage all the lowest
        for(int i = 0; i < highest.size(); i++){
            for(int j = 0; j < lowest.size(); j++){
                Helper.printIf( desc,
                                playerRolls[highest.get(i).intValue()].getOwner().getName()
                                + " deals " + 
                                playerRolls[highest.get(i).intValue()].getOwner().getDmg()
                                + " damage to " +
                                playerRolls[lowest.get(j).intValue()].getOwner().getName()
                                + ".");
                // This line is a nightmare
                // It gets both players from the rolls from the array based on the numbers in arraylists
                playerRolls[highest.get(i).intValue()].getOwner().dealDmg(playerRolls[lowest.get(j).intValue()].getOwner(), desc);
            }
        }
    }

    // Finds the highest value in the array of rolls
    int findHighest(){
        int highest = -1; 
        int i;
        int highestIndex = 0;
        
        for(i = 0; i < playerRolls.length; i++){
            if(playerRolls[i].getCurrent() > highest){
                highest = playerRolls[i].getCurrent();
                highestIndex = i;
            }
        }
        return highest;
    }
    
    // Finds the lowest value in the array of rolls
    int findLowest(){
        int lowest = 9999; 
        int i;
        int lowestIndex = 0;
        
        for(i = 0; i < playerRolls.length; i++){
            if(playerRolls[i].getCurrent() < lowest){
                lowest = playerRolls[i].getCurrent();
                lowestIndex = i;
            }
        }
        return lowest;
    }
    
    // Puts all instances of a given value roll into an ArrayList
    // The name is slightly misleading, as it can take and find any value- it's just originally meant for the highest and lowest to be plugged in.
    ArrayList<Integer> getIndices(int target){

        ArrayList<Integer> indices = new ArrayList<Integer>();

        for(int i = 0; i < playerRolls.length; i++){
            if(playerRolls[i].getCurrent() == target){
                indices.add(i);
            }
        }

        return indices;
    }
}
