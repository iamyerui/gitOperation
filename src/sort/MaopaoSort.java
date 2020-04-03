package sort;

import java.util.Arrays;

public class MaopaoSort {
    public static void main(String[] args) {
        int[] arry = new int[]{12, 33, 28, 86, 15, 62, 9, 38};
        int temp = 0;

        for (int i = 0; i < arry.length - 1; i++) {
            for (int j = 0; j < arry.length - 1 - i; j++) {
                if (arry[j] < arry[j + 1]) {
                    temp = arry[j];
                    arry[j] = arry[j + 1];
                    arry[j + 1] = temp;
                }
            }

        }

        System.out.println(Arrays.toString(arry));
    }
}
