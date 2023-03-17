package array;

import java.util.Arrays;

public class ArrayRunner {
    public static void main(String[] args) {
        int[][] arr = new int[10][10];
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++){
                arr[i][j] = 10-i-j;
            }
        }
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("arr = " + arr);
        for (int[] ints : arr) {
            Arrays.sort(ints);
        }

        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

    }

}
