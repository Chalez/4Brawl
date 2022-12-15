import java.util.Arrays;

/**
 * The brawl itself, containing many players who take part in repeated rounds.
 * The brawl takes in the participating players as an array, and can be run multiple times.
 * When run, the brawl returns the name of the winning player.
 */
public class Brawl
{
    // instance variables - replace the example below with your own
    Player[] players;
    boolean desc;

    /** Full Constructor, with an array of players and whether the brawl is printed. */
    public Brawl(Player[] p, boolean d)
    {
        players = p;
        desc = d;
    }
    
    /** Constructor, with an array of players and printing turned off. */
    public Brawl(Player[] p)
    {
        this(p, false);
    }

    /** Runs the brawl and returns the name of the winner. */
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
            
            // Gives each player a chance to respond, twice.
            // # of chances can be increased if number of responses does
            for(int j = 0; j < 2; j++){
                for(int i = 0; i < r.length; i++){
                    r[i].getOwner().respond(i, r);
                }
            }
            
            Helper.printIf(desc, "Round " + roundsCount + ": " + Arrays.toString(r));
            new Round(r, desc).run();
        }
        
        return "Default (Something broke?)";
    }
    
    /** Resets each player in the brawl to default. */
    public void resetPlayers(){
        for(int i = 0; i < players.length; i++){
            players[i].reset();
        }
    }
    
    /** Returns the number of currently living players in the brawl. */
    public int livingPlayers(){
        int count = 0;
        for(int i = 0; i < players.length; i++){
            if(!players[i].isDead()){
                count++;
            }
        }
        return count;
    }
    
    /** Returns the first living player in the Brawl's array. Meant to be called when only 1 player is left. */
    public Player lastPlayer(){
        for(int i = 0; i < players.length; i++){
            if(!players[i].isDead()){
                return players[i];
            }
        }
        return null;
    }
}
