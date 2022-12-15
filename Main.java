
/**
 * The main method which creates and runs the brawls.
 * This is the one you should be editing!
 */
public class Main
{
    /**
    * Simulates a brawl defined by the code many times, then prints the results.
    */
    public static void multiTest()
    {
        // The Runner is what tracks the result of each brawl.
        Runner r = new Runner();
        
        // The number of brawls simulated to find the odds.
        // THIS CAN BE SAFELY CHANGED
        int iterations = 10000;
        
        // A list of players which could be included in brawls.
        // The order of the constructor is name, health, dmg, + or - to rolls...
        // ... And an optional list of attributes for more complex effects.
        // THESE CAN BE SAFELY CHANGED
        Player a = new Player("Player A", 2, 1, 0);
        Player b = new Player("Player B", 2, 1, 0);
        Player c = new Player("Player C", 2, 1, 0);
        Player d = new Player("Player D", 2, 1, -1);
        Player e = new Player("Player E", 2, 1, 0, new String[]{"tiebreak"});
        Player f = new Player("Player F", 2, 1, 1, new String[]{"timedModDown", "timedModDown"});
        
        // Creates a brawl using the players defined above
        // THE NUMBER OF PLAYERS AND WHICH PLAYERS PARTICIPATE CAN BE SAFELY CHANGED
        Brawl q = new Brawl(new Player[]{c, b});
        
        // The brawl is run multiple times and the results of each are stored.
        for (int i = 0; i < iterations; i++){
            r.addResult(q.run());
        }
        
        // The results are printed.
        r.print();
        System.out.println();
    }
    
    /**
    * Prints a detailed summary of a single simulated brawl defined by the code.
    */
    public static void descTest(){
        // The Runner is what tracks the result of each brawl.
        Runner r = new Runner();
        
        // A list of players which could be included in brawls.
        // The order of the constructor is name, health, dmg, + or - to rolls...
        // ... And an optional list of attributes for more complex effects.
        // THESE CAN BE SAFELY CHANGED
        Player a = new Player("Player A", 2, 1, 0);
        Player b = new Player("Player B", 2, 1, 0);
        Player c = new Player("Player C", 2, 1, 0);
        Player d = new Player("Player D", 2, 1, 0);
        Player e = new Player("Player E", 2, 1, 0, new String[]{"tiebreak"});
        Player f = new Player("Player F", 2, 1, 1, new String[]{"timedModDown", "timedModDown"});
        
        // Creates a brawl using the players defined above
        // THE NUMBER OF PLAYERS AND WHICH PLAYERS PARTICIPATE CAN BE SAFELY CHANGED
        Brawl q = new Brawl(new Player[]{a, b, c}, true);
        
        // The results are printed.
        q.run();
        System.out.println();
    }
}
