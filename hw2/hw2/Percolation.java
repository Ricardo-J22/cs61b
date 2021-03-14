package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    private int N;
    private boolean[][] universe;
    private WeightedQuickUnionUF topUnion;
    private WeightedQuickUnionUF botUnion;
    private int number;
    private int topSite;
    private int botSite;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Parameter cannot be a number <= 0");
        }
        universe = new boolean[N][N];
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                universe[i][j] = false;
            }
        }
        this.N = N;
        number = 0;
        topUnion = new WeightedQuickUnionUF(N * N + 1);
        topSite = N * N ;
        botUnion = new WeightedQuickUnionUF(N * N + 2);
        botSite = N * N + 1;
        for (int i = 0; i < N; i++) {
            topUnion.union(topSite, xyto1D(0, i));
        }
        for (int i = 0; i < N; i++) {
            botUnion.union(botSite, xyto1D(N - 1, i));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (universe[row][col]) {
            return;
        }
        universe[row][col] = true;
        number++;
        checkNeighbor(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return universe[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if(!isOpen(row, col)){
            return false;
        }
        return topUnion.connected(topSite, xyto1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return number;
    }

    // does the system percolate?
    public boolean percolates() {
        if (number == 0){
            return false;
        }
        return botUnion.connected(topSite, botSite);
    }

    private int xyto1D(int x, int y) {
        return N * y + x + 1;
    }

    private void checkNeighbor(int x, int y){
        checkNeighborHelper(x, y, x + 1, y);
        checkNeighborHelper(x, y, x - 1, y);
        checkNeighborHelper(x, y, x, y - 1);
        checkNeighborHelper(x, y, x, y + 1);
    }
    private void checkNeighborHelper(int x , int y, int newX, int newY) {
        if (newX < 0 || newX > N-1 || newY < 0 || newY > N-1){
            return;
        }
        if (universe[newX][newY]){
            topUnion.union(xyto1D(x, y), xyto1D(newX, newY));
            botUnion.union(xyto1D(x, y), xyto1D(newX, newY));
        }
    }

    
}
