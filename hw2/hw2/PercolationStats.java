package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private int T;
    private double[] openSiteFraction;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation exper =  pf.make(N);
            while (!exper.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                if (exper.isOpen(x, y)){
                    continue;
                }
                exper.open(x, y);
            }
            openSiteFraction [i] = (double)exper.numberOfOpenSites()/ (N * N);
        }
        

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSiteFraction);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openSiteFraction);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
    public static void main(String[] args) {
        
    }
}
