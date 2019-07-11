package sort.bubblesort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr={3,9,-1,10,20};
        sort(arr);
    }

    private static void swap(int[] arr,int i){
        int temp=arr[i];
        arr[i]=arr[i+1];
        arr[i+1]=temp;
    }

    //O(n^2)
    private static void sort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag=false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    flag=true;
                    swap(arr,j);
                }
            }
            System.out.println("第"+(i+1)+"次排序后的数组");
            System.out.println(Arrays.toString(arr));
            //如果在一次循环中，没有交换，直接结束。
            if (!flag)
                break;
        }
    }
}
