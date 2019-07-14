package sort.shellsort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        //测试正确性
        testMethod();
        //测试移动法和交换法速度
        testSpeed();
    }

    private static void moveSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertVal = arr[insertIndex];
                while (insertIndex - gap >= 0 && insertVal < arr[insertIndex - gap]) {
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                arr[insertIndex] = insertVal;

            }
            //System.out.println("第一轮后" + Arrays.toString(arr));
        }
    }


    private static void swapSort(int[] arr) {
        //一共gap个组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap])
                        swap(arr, j, j + gap);
                }
            }
            //System.out.println("第一轮后"+ Arrays.toString(arr));
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void testMethod() {
        int[] arr1 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        moveSort(arr1);
        System.out.println("移动法排序结果 :" + Arrays.toString(arr1));
        int[] arr2 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        swapSort(arr2);
        System.out.println("交换法排序结果:" + Arrays.toString(arr2));
    }

    private static void testSpeed() {
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000);

        }
        long t1 = System.currentTimeMillis();
        moveSort(arr);
        long t2 = System.currentTimeMillis();
        System.out.println("移动法所耗时间:" + (t2 - t1) + " ms");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000);

        }
        t1 = System.currentTimeMillis();
        swapSort(arr);
        t2 = System.currentTimeMillis();
        System.out.println("交换法毫秒数  :" + (t2 - t1) + " ms");
    }
}
