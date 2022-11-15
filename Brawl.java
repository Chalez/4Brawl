import java.util.Arrays;

/**
 * Write a description of class Brawl here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Brawl
{
    // instance variables - replace the example below with your own
    Player[] players;
    boolean desc;

    /**
     * Constructor for objects of class Brawl
     */
    public Brawl(Player[] p, boolean d)
    {
        players = p;
        desc = d;
    }
    
    public Brawl(Player[] p)
    {
        this(p, false);
    }

    public String run(){
        resetPlayers();
        boolean flag = true;
        int roundsCount = 0;
        while(flag){
            int count = livingPlayers();
            int q = 0;
            roundsCount++;
            
            if(count == 0){
                Helper.printIf(desc, "All players are dead- Draw.");
                return "Draw";
            }
            else if(count == 1){
                Helper.printIf(desc, lastPlayer().getName() + " Wins.");
                return lastPlayer().getName();
            }
            
            if (roundsCount > 50){
                Helper.printIf(desc, "Too many rounds, softlock prevention triggered.");
                return "Softlock";
            }
            
            Roll[] r = new Roll[count];
            
            for(int i = 0; i < players.length; i++){
                if(!players[i].isDead()){
                    r[q] = players[i].roll();
                    q++;
                }
                //r[i] = players[i].roll();
            }
            Helper.printIf(desc, "Round " + roundsCount + ": " + Arrays.toString(r));
            new Round(r, desc).run();
        }
        
        return "Default (Something broke?)";
    }
    
    public void resetPlayers(){
        for(int i = 0; i < players.length; i++){
            players[i].reset();
        }
    }
    
    // Returns the number of players currently alive in the brawl
    public int livingPlayers(){
        int count = 0;
        for(int i = 0; i < players.length; i++){
            if(!players[i].isDead()){
                count++;
            }
        }
        return count;
    }
    
    //returns the first living player it finds. Call only if 1 left
    public Player lastPlayer(){
        for(int i = 0; i < players.length; i++){
            if(!players[i].isDead()){
                return players[i];
            }
        }
        return null;
    }
}
