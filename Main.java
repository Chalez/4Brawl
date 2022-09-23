
/**
 * A playground to test that rolling and runner work
 */
public class Main
{
    public static void sampleMethod()
    {
        Runner r = new Runner();
        for (int i = 0; i < 9999; i++){
            Roll a = new Roll(-2);
            r.addResult(String.valueOf(a.getCurrent()));
        }
        // r.addResult("a");
        // r.addResult("b");
        // r.addResult("c");
        // r.addResult("d");
        r.print();
        System.out.println();
    }
}
