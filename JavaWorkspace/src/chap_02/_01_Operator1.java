package chap_02;

public class _01_Operator1 {
    public static void main(String[] args) {
        String[] arr = new String[3];
        for (int i=0;i<3;i++) {
            arr[i] = Integer.toString(i);
        }

        String[] arr2 = new String[] {"hello", "my", "name"};

        for (int i=0;i<3;i++) {
            System.out.println(arr[i]);
            System.out.println(arr2[i]);
        }

        int[][] board = new int[20][20];


    }
}
