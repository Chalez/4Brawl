
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

    /**
     * Constructor for objects of class Brawl
     */
    public Brawl(Player[] p)
    {
        players = p;
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
                return "Draw";
            }
            else if(count == 1){
                return lastPlayer().getName();
            }
            
            if (roundsCount > 50){
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
            new Round(r).run();
        }
        
        return "Player A";
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
