import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedqueue = new RandomizedQueue<>();
        for(int i = 0;i<k;i++) {
            randomizedqueue.enqueue(StdIn.readString());
        }
        for(String str : randomizedqueue) {
            StdOut.println(str);
        }
    }
}
