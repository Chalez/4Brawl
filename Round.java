
/**
 * Write a description of class Round here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.util.ArrayList;






public class Round
{

    Roll[] playerRolls;
    

    public Round(Roll[] rolls){
        playerRolls = rolls;
    }
    
    void run(){
        ArrayList<Integer> highest = new ArrayList<Integer>();
        ArrayList<Integer> lowest = new ArrayList<Integer>();
        
        highest = getIndices(findHighest());
        lowest = getIndices(findLowest());
        
        for(int i = 0; i < highest.size(); i++){
            for(int j = 0; j < lowest.size(); j++){
                // This line is a nightmare
                // It gets both players from the rolls from the array based on the numbers in arraylists
                playerRolls[highest.get(i).intValue()].getOwner().dealDmg(playerRolls[lowest.get(j).intValue()].getOwner());
            }
        }
    }

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


        System.out.println("highest is " + highest + " at " + highestIndex);

        return highest;
    }

    //I realized the highest and lowest indices classes were the same thing- so, I generalized.
    ArrayList<Integer> getIndices(int target){

        ArrayList<Integer> indices = new ArrayList<Integer>();

        for(int i = 0; i < playerRolls.length; i++){
            if(playerRolls[i].getCurrent() == target){
                indices.add(i);
            }
        }

        return indices;
    }

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

        System.out.println("lowest is " + lowest + " at " + lowestIndex);

        return lowest;
    }
    
    // public static void main(String[] args){

        // int[] sample = {5, 3, 1, 1, 2, 1, 4, 5, 5, 4, 5};

        // Round r = new Round(sample);

        // ArrayList<Integer> highestList = r.getIndices(r.findHighest());

        // ArrayList<Integer> lowestList =  r.getIndices(r.findLowest());

        // System.out.println(highestList.toString());

        // System.out.println();

        // System.out.println(lowestList.toString());

    // }
}
