package sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        int a[] = new int[] {89,12,44,23,78,1};
        System.out.println(Arrays.toString(insertSort(a)));
    }
    public static int[] insertSort(int[] arr){
        if(arr == null || arr.length < 2)
            return arr;

        int n = arr.length;
        for (int i = 1; i < n; i++)
        {
            int temp = arr[i];
            int k = i-1 ;
            while (k >=0 && arr[k] > temp)
            {
                k--;
            }

            for (int j=i; j > k+1; j--)
            {
                arr[j] = arr[j-1];
            }
            arr[k+1] = temp;
        }

        return  arr;
    }
}
