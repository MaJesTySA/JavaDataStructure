package sort.selectsort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        sort(arr);
    }

    private static void swap(int[] arr, int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //O(n^2)
    //选择排序每一次循环将最小（大）值找出来，然后放在数组首位。
    //一次循环，只有一次交换，或者不交换。比冒泡排序更快。
    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag=false;
            int minIndex=i;
            //找到最小值所对应的索引
            for (int j = i+1; j < arr.length; j++) {
                if (arr[minIndex]>arr[j]){
                    minIndex=j;
                }
            }
            //与循环的第一个元素交换
            if (minIndex!=i){
                flag=true;
                swap(arr,i,minIndex);
            }
            System.out.println("第"+(i+1)+"次排序后的数组");
            System.out.println(Arrays.toString(arr));
            if (!flag)
                break;
        }
    }
}
