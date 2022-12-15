
/**
 * A Round is a piece of a brawl where every player rolls a dice.
 * Players who roll the highest damage players who roll the lowest.
 * Each Round object takes in an array of rolls, runs, and then is discarded.
 */
import java.util.ArrayList;

public class Round
{
    Roll[] playerRolls;
    boolean desc;
    
    /** Full Constructor, with an array of rolls and whether the round is printed. */
    public Round(Roll[] rolls, boolean d){
        playerRolls = rolls;
        desc = d;
    }
    
    /** Constructor with an array of rolls and printing turned off. */
    public Round(Roll[] rolls){
        this(rolls, false);
    }
    
    /** Resolves the round, causing players who rolled the highest to damage players who rolled the lowest. */
    void run(){
        // Creates arraylists to store the highest and lowest rolls
        ArrayList<Integer> highest = new ArrayList<Integer>();
        ArrayList<Integer> lowest = new ArrayList<Integer>();
        
        // Puts the highest and lowest rolls into the arraylists
        highest = getIndices(findHighest());
        lowest = getIndices(findLowest());
        
        // Detects a player with Tiebreak and adjusts arrays accordingly
        // It is assumed only one player can have tiebreak, as it is character exclusive
        int callowInd = -1;
        for(int i = 0; i < playerRolls.length; i++){
            if(playerRolls[i].getOwner().getAttribute("tiebreak") != -1){
                callowInd = i;
            }
        }
        if( callowInd != -1 && 
            playerRolls[callowInd].getCurrent() == findHighest() &&
            getIndices(findHighest()).size() > 1){
            highest = new ArrayList<Integer>();
            highest.add(callowInd);
            //System.out.println("High tiebreak!");
        }
        if( callowInd != -1 &&
            playerRolls[callowInd].getCurrent() == findLowest() &&
            getIndices(findLowest()).size() > 1){
            lowest.remove(playerRolls[callowInd]);
            //System.out.println("Low tiebreak!");
        }
        
        // All the highest damage all the lowest
        for(int i = 0; i < highest.size(); i++){
            for(int j = 0; j < lowest.size(); j++){
                Helper.printIf( desc && highest.get(i) != lowest.get(j),
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

    /** Outputs the highest value in the Round's array of rolls. */
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
    
    /** Outputs the lowest value in the Round's array of rolls. */
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
    
    /** Returns an arraylist containing the index of every roll in the base array with a given value. */
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
