
/**
 * Write a description of class Round here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.util.ArrayList;






public class Round
{

    int[] playerRolls;


    public Round(int[] rolls){

        playerRolls = rolls;

    }

    int findHighest(){

        int highest = -1; 
        int i;
        int highestIndex = 0;
        

        for(i = 0; i < playerRolls.length; i++){

            if(playerRolls[i] > highest){
                highest = playerRolls[i];
                highestIndex = i;
            }

        }


        System.out.println("highest is " + highest + " at " + highestIndex);

        return highest;
    }


    ArrayList<Integer> getHighestIndices(int highest){

        ArrayList<Integer> highestIndices = new ArrayList<Integer>();

        for(int i = 0; i < playerRolls.length; i++){
            if(playerRolls[i] == highest){
                highestIndices.add(i);
            }
        }

        return highestIndices;
    }

    ArrayList<Integer> getLowestIndices(int lowest){

        ArrayList<Integer> lowestIndices = new ArrayList<Integer>();

        for(int i = 0; i < playerRolls.length; i++){
            if(playerRolls[i] == lowest){
                lowestIndices.add(i);
            }
        }

        return lowestIndices;
    }


    int findLowest(){
        int lowest = 9999; 
        int i;
        int lowestIndex = 0;
        

        for(i = 0; i < playerRolls.length; i++){

            if(playerRolls[i] < lowest){
                lowest = playerRolls[i];
                lowestIndex = i;
            }

        }

        System.out.println("lowest is " + lowest + " at " + lowestIndex);

        return lowest;
    }
    


    public static void main(String[] args){

        int[] sample = {5, 3, 1, 1, 2, 1, 4, 5, 5, 4, 5};

        Round r = new Round(sample);

        ArrayList<Integer> highestList = r.getHighestIndices(r.findHighest());

        ArrayList<Integer> lowestList =  r.getLowestIndices(r.findLowest());

        System.out.println(highestList.toString());

        System.out.println();

        System.out.println(lowestList.toString());

    }




}
