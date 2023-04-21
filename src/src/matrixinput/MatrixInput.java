package src.matrixinput;

import java.io.File;
import java.util.Scanner;

public class MatrixInput {
    public int[][] include(){
        try {
            Scanner input = new Scanner(new File("src/mat01.txt"));
            int n = input.nextInt();
            int[][] a = new int[n][n];
            while (input.hasNextInt()) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++)
                        a[i][j] = input.nextInt();
                }

            }
            /*

             System.out.println("The input sorted matrix is : ");
             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < n; j++) {
                     System.out.print(a[i][j] + " ");
                 }
                 System.out.println();
             }
            */

            return a;
        } catch (Exception e){
            e.getMessage();
            return null;
        }
    }
}
