/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int t;
    private int total;
    private double[] samples;
    private double sampleCount;
    private int size;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        int rand1;
        int rand2;
        t = trials;
        size = n*n;
        samples = new double[trials];
        for (int i = 0; i<trials; i++){
            Percolation test = new Percolation(n);
            sampleCount=0;
            while (!test.percolates()){
                rand1 = StdRandom.uniform(1,n+1);
                rand2 = StdRandom.uniform(1,n+1);
                if (!test.isOpen(rand1, rand2)) {
                    test.open(rand1, rand2);
                    sampleCount++;
                }
            }
            total += sampleCount;
            samples[i] = sampleCount/size;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
       // return ((double)total/size)/t;
        return StdStats.mean(samples);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        // double stdSquared = 0.0;
        // for (int i = 0; i< t; i++){
        //     stdSquared += Math.pow(((double)samples[i]/size) - mean(), 2);
        // }
        // stdSquared = stdSquared/(t-1);
        // double s = Math.pow(stdSquared, 0.5);
        // return s;
        return StdStats.stddev(samples);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - (1.96*stddev()/Math.pow(t, 0.5));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + (1.96*stddev()/Math.pow(t, 0.5));
    }

    // test client (see below)
    public static void main(String[] args){
        //add error checks?
        int num = Integer.parseInt(args[0]);
        int tri = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(num, tri);
        System.out.println("mean                    = " + test.mean());
        System.out.println("stdDev                  = " + test.stddev());
        System.out.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
    }
}
