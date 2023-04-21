import src.cliquenumber.CliqueNumber;
import src.matrixinput.MatrixInput;

public class Main {
    public static void main(String[] args) {
        MatrixInput mi = new MatrixInput();
        int[][] adjMat = mi.include();

        CliqueNumber CN = new CliqueNumber();
        int cliqueNumber = CN.cliqueNumber(adjMat);

        System.out.printf(String.valueOf(cliqueNumber));
    }
}