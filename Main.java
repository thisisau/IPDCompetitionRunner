import agents.*;
import runner.Runner;

public class Main {
    public static void main(String[] args) {
        Runner runner = new Runner(new LitRando(), new XX(), new TFTADV(), new Decision(), new Detective());
        runner.run();

    }
}