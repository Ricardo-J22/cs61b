package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Stack;

public class Solver {
    private class SearchNode {
        private WorldState state;
        private int move = 0;
        private SearchNode prev;

        /**
         * @param state
         * @param move
         * @param prev
         */
        public SearchNode(WorldState state, int move, SearchNode prev) {
            this.state = state;
            this.move = move;
            this.prev = prev;
        }

    }

    private ArrayList<WorldState> solution;

    /**
     * Constructor which solves the puzzle, computing everything necessary for
     * moves() and solution() to not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * 
     * @param initial
     */
    public Solver(WorldState initial) {
        MinPQ<SearchNode> PQ = new MinPQ<>(1, new NodeComparator());
        solution = new ArrayList<>();
        SearchNode currentNode = new SearchNode(initial, 0, null);
        PQ.insert(currentNode);
        while (!PQ.isEmpty()) {
            currentNode = PQ.delMin();
            if (currentNode.state.isGoal()) {
                break;
            }
            for (WorldState a : currentNode.state.neighbors()) {
                SearchNode NextSearchNode = new SearchNode(a, currentNode.move + 1, currentNode);
                if ( currentNode.prev!= null && a.equals(currentNode.prev.state)) {
                    continue;
                }
                PQ.insert(NextSearchNode);
            }
        }
        Stack<WorldState> path = new Stack<>();
        for (SearchNode ptr = currentNode; ptr != null; ptr = ptr.prev) {
            path.push(ptr.state);
        }
        while (!path.isEmpty()) {
            solution.add(path.pop());
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting at the
     * initial WorldState.
     */
    public int moves() {
        return solution.size()-1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState to the
     * solution.
     */
    public Iterable<WorldState> solution() {
        return solution;
    }

    private class NodeComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            // TODO Auto-generated method stub
            int prior1 = o1.move + edtg(o1);
            int prior2 = o2.move + edtg(o2);
            return prior1 - prior2;
        }

        public int edtg(SearchNode node) {
            return node.state.estimatedDistanceToGoal();
        }

    }
}