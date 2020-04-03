package sort;

import java.util.Arrays;

public class SelectSort {


    public static void main(String[] args) {
        int a[] = new int[] {89,12,44,23,78,1};
        System.out.println(Arrays.toString(selectSort(a)));
    }
        public static int[] selectSort(int[] a) {

            int len = a.length;
            for (int i = 0; i < len -1; i++) {
                int min = i;
                for (int j = i + 1; j < len ; j++) {
                    if (a[min] > a[j])
                    {
                        min = j;
                    }
                }

                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }

            return a;
        }
    }
