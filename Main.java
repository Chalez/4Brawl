
/**
 * A playground to test that rolling and runner work
 */
public class Main
{
    public static void sampleMethod()
    {
        Runner r = new Runner();
        Player a = new Player("Player A", 2, 1, 0);
        Player b = new Player("Player B", 2, 1, -1);
        Player c = new Player("Player C", 2, 1, -1);
        Player d = new Player("Player D", 2, 1, -1);
        Player e = new Player("Player E", 2, 1, 0);
        Player f = new Player("Player F", 2, 1, 0);
        
        Brawl q = new Brawl(new Player[]{a, b, c, d});
        for (int i = 0; i < 10000; i++){
            r.addResult(q.run());
        }
        // r.addResult("a");
        // r.addResult("b");
        // r.addResult("c");
        // r.addResult("d");
        r.print();
        System.out.println();
    }
}
