package sort.mergesort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左
            sort(arr, left, mid, temp);
            //向右
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }


    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0; //临时数组temp的当前索引

        //1. 先把左右两边的数据按照规则填充到temp数组，
        //   直到左右两边有一边处理完毕为止。
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //2. 把有剩余数据的一边的数据全部填充到temp数组。
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        //3. 将temp数组元素拷贝到原数组。
        //   并不是每次都拷贝所有元素
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
