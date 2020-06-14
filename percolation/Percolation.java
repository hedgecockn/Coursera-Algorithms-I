/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
            private WeightedQuickUnionUF wqu;
            private WeightedQuickUnionUF wquNoBot;
            private boolean[][] grid;
            private int size;
            private int virtualTop;
            private int virtualBottom;
            private int openCount = 0;

            // creates n-by-n grid, with all sites initially blocked
            public Percolation(int n) {
                if (n <= 0)
                    throw new IllegalArgumentException();
                grid = new boolean[n][n];
                wqu = new WeightedQuickUnionUF(n*n + 2);
                wquNoBot = new WeightedQuickUnionUF(n*n + 1);
                size = n;
                virtualTop = n*n;
                virtualBottom = n*n + 1;
            }

            //throws error if a space is valid a space is valid
            private void checkIfValid(int row, int col) {
                if ((row<=0 || row>size || col<=0 || col>size))
                    throw new IllegalArgumentException();
            }

            // opens the site (row, col) if it is not open already
            public void open(int row, int col) {
                checkIfValid(row, col);
                if(!grid[row - 1][col - 1]) {
                    grid[row - 1][col - 1] = true;
                    int center = ((row - 1) * size + col - 1);
                    int left = center - 1;
                    int right = center + 1;
                    int up = center - size;
                    int down = center + size;
                    openCount++;
                    if (row == 1) {
                        wqu.union(center, virtualTop);
                        wquNoBot.union(center, virtualTop);
                    }
                    if (row == size) {
                        wqu.union(center, virtualBottom);
                    }
                    if (col != 1) {
                        if (isOpen(row, col - 1)) {
                            wqu.union(center, left);
                            wquNoBot.union(center, left);
                        }
                    }
                    if (col < size) {
                        if (isOpen(row, col + 1)) {
                            wqu.union(center, right);
                            wquNoBot.union(center, right);
                        }
                    }
                    if (row != 1) {
                        if (isOpen(row - 1, col)) {
                            wqu.union(center, up);
                            wquNoBot.union(center, up);
                        }
                    }
                    if (row != size) {
                        if (isOpen(row + 1, col)) {
                            wqu.union(center, down);
                            wquNoBot.union(center, down);
                        }
                    }
                }
            }

            // is the site (row, col) open?
            public boolean isOpen(int row, int col) {
                checkIfValid(row, col);
                return grid[row-1][col-1];
            }

            // is the site (row, col) full?
            public boolean isFull(int row, int col) {
                checkIfValid(row, col);
                int space = ((row-1)*size + col-1);
                if (isOpen(row, col))
                    return wquNoBot.find(space) == wquNoBot.find(virtualTop);
                return false;
            }

            // returns the number of open sites
            public int numberOfOpenSites() {
                return openCount;
            }

            // does the system percolate?
            public boolean percolates() {
                return wqu.find(virtualBottom) == wqu.find(virtualTop);
            }

            // test client (optional)
            public static void main(String[] args){
                // Percolation testPerc = new Percolation(2);
                // testPerc.open(1,1);
                // testPerc.open(2,1);
                // System.out.println(testPerc.percolates());
        }

    }
