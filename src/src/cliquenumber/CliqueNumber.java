package src.cliquenumber;

import java.util.ArrayList;
import java.util.List;


public class CliqueNumber {
    int maxCliqueSize;
    public int cliqueNumber(int[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;
        maxCliqueSize = 1;
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }
        List<Integer> solution = new ArrayList<>();
        branchAndBound(adjacencyMatrix, candidates, solution);
        return maxCliqueSize;
    }

    private void branchAndBound(int[][] adjacencyMatrix, List<Integer> candidates, List<Integer> solution) {
        if (candidates.isEmpty()) {
            maxCliqueSize = Math.max(maxCliqueSize, solution.size());
            return;
        }
        int candidate = candidates.get(0);
        List<Integer> newCandidates = new ArrayList<>();
        for (int i = 1; i < candidates.size(); i++) {
            int vertex = candidates.get(i);
            if (adjacencyMatrix[candidate][vertex] == 1) {
                newCandidates.add(vertex);
            }
        }
        solution.add(candidate);
        branchAndBound(adjacencyMatrix, newCandidates, solution);
        solution.remove(solution.size() - 1);
        candidates.remove(0);
        branchAndBound(adjacencyMatrix, candidates, solution);
    }

}
