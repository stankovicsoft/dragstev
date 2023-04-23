package src.matrixinput;

import java.io.File;
import java.util.Scanner;

public class MatrixInput {
    public int[] degree;
    public int[][] include(){
        try {
            Scanner input = new Scanner(new File("src/mat01.txt"));
            int n = input.nextInt();

            degree = new int[n];
            for(int i = 0; i < n; i++){
                degree[i] = 0;
            }

            int[][] a = new int[n][n];
            while (input.hasNextInt()) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        a[i][j] = input.nextInt();
                        if(i > j) {
                            this.degree[i]++;
                        }
                    }
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
            System.out.println(e.getMessage());
            return null;
        }
    }
}
