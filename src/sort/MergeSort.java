package sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int a[] = new int[] {89,12,44,23,78,1};
        System.out.println(Arrays.toString(mergeSort(a, 0, 5)));
    }

    public static int[] mergeSort(int[] arr, int left, int right)
    {
        if (left < right)
        {
            int mid = (left + right)/2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);
            merge(arr, left, mid, right);
        }

        return arr;
    }

    public static void merge(int[] arr, int left, int mid, int right)
    {
        int[] newInts = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k=0;

        while(i <= mid && j <= right)
        {
            if (arr[i] < arr[j]){
                newInts[k++] = arr[i++];
            }else {
                newInts[k++] = arr[j++];
            }
        }

        while (i <= mid) {newInts[k++] = arr[i++];}
        while (j <= right) {newInts[k++] = arr[j++];}

        for (int l = 0; l < k; l++) {
            arr[left++] = newInts[l];
        }

//        arr = Arrays.copyOf(newInts, right - left + 1);

    }

}
