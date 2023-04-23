import src.cliquenumber.CliqueNumber;
import src.cliquenumber2.CliqueNumber2;
import src.matrixinput.MatrixInput;

public class Main {
    public static void main(String[] args) {
        MatrixInput mi = new MatrixInput();
        int[][] adjMat = mi.include();

        CliqueNumber CN = new CliqueNumber();
        int cliqueNumber = CN.cliqueNumber(adjMat);
        System.out.println(String.valueOf(cliqueNumber));


        CliqueNumber2 CN2 = new CliqueNumber2(adjMat, mi.degree, 1);
        int[] sol = CN2.giveResult();
        System.out.printf(String.valueOf(sol.length));
        System.out.printf(String.valueOf(sol[0]));
        System.out.printf(String.valueOf(sol[1]));
        System.out.printf(String.valueOf(sol[2]));
    }
}