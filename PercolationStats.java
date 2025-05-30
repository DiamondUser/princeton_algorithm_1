import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    int size;
    double[] rates;
    final double CONFIDENCE_95 = 1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if(n<=0)
        {
            throw new IllegalArgumentException("expect the n > 0");
        }
        if(trials<=0)
        {
            throw new IllegalArgumentException("expect the trials > 0");
        }
        rates = new double[trials];
        for(int i = 0;i < trials; i++)
        {
            Percolation percolation = new Percolation(n);
            while(!percolation.percolates())
            {
                int random_row = StdRandom.uniformInt(1,n+1);     //get a random_row in [1,n]
                int random_col = StdRandom.uniformInt(1,n+1);     //get a random_col in [1,n]
                if(!percolation.isOpen(random_row,random_col))
                {
                    percolation.open(random_row,random_col);
                }
            }
            rates[i] = 1.0 * percolation.numberOfOpenSites() / (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(rates);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(rates);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(rates.length);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(rates.length);
    }
    // test client (see below)
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n,trials);
        StdOut.println("To simulate the percolation in a "+ n + "x"+ n + " grid "+trials+" times,the 95% confidence of the threshold is:");
        StdOut.println(percolationStats.confidenceLo() + " to " + percolationStats.confidenceHi());
    }

}