import src.cliquenumber.CliqueNumber;

public class Main {
    public static void main(String[] args) {
        int[][] adjMat = {{0,1,1,1},{1,0,1,1},{1,1,0,1},{1,1,1,0}};
        CliqueNumber CN = new CliqueNumber();
        int cliqueNumber = CN.cliqueNumber(adjMat);

        System.out.printf(String.valueOf(cliqueNumber));
    }
}