package lab11.graphs;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> queue;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        queue = new ArrayDeque<>();
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        queue.add(v);
        marked[v] = true;
        announce();
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            if (temp == t) {
                targetFound = true;
                return;
            }
            for (int i : maze.adj(temp)) {
                if (!marked[i]) {

                    marked[i] = true;
                    queue.add(i);
                    edgeTo[i] = temp;
                    distTo[i] = distTo[temp] + 1;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        // bfs();
        bfs(s);
    }
}

