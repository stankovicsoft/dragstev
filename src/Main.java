import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int cliqueNumber = cliqueNumberBB(adjMat);
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }
    }

    public static int cliqueNumberBB(int[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = i;
        }
        Arrays.sort(candidates, (a, b) -> -Integer.compare(countOnes(adjacencyMatrix[a]), countOnes(adjacencyMatrix[b])));
        int maxCliqueSize = 1;
        List<Integer> currentClique = new ArrayList<>();
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            remaining.add(i);
        }
        branchAndBound(adjacencyMatrix, candidates, currentClique, remaining, maxCliqueSize);
        return maxCliqueSize;
    }

    private static void branchAndBound(int[][] adjacencyMatrix, int[] candidates, List<Integer> currentClique, List<Integer> remaining, int maxCliqueSize) {
        if (currentClique.size() + remaining.size() <= maxCliqueSize) {
            return;
        }
        if (remaining.isEmpty()) {
            maxCliqueSize = currentClique.size();
            return;
        }
        int v = remaining.get(0);
        remaining.remove(0);
        if (isClique(adjacencyMatrix, currentClique, v)) {
            currentClique.add(v);
            maxCliqueSize = Math.max(maxCliqueSize, currentClique.size());
            branchAndBound(adjacencyMatrix, candidates, currentClique, remaining, maxCliqueSize);
            currentClique.remove(currentClique.size() - 1);
        }
        if (currentClique.size() + remaining.size() <= maxCliqueSize) {
            return;
        }
        branchAndBound(adjacencyMatrix, candidates, currentClique, remaining, maxCliqueSize);
    }

    private static boolean isClique(int[][] adjacencyMatrix, List<Integer> currentClique, int v) {
        for (int u : currentClique) {
            if (adjacencyMatrix[u][v] == 0) {
                return false;
            }
        }
        return true;
    }

    private static int countOnes(int[] row) {
        int count = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1) {
                count++;
            }
        }
        return count;
    }

}