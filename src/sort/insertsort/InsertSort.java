package sort.insertsort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        //测试三种方法的正确性
        testMethod();
        //测试移动法和交换法的速度
        testSpeed();
    }

    //O(N~N^2)
    private static void moveSort(int[] arr) {
        int insertVal;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertVal + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            // System.out.println("第"+i+"轮插入");
            //System.out.println(Arrays.toString(arr));
        }
    }

    private static void swapSort_1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int inserVal = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > inserVal) {
                    swap(arr, j, j + 1);
                }
            }
//            System.out.println("第" + i + "轮插入");
//            System.out.println(Arrays.toString(arr));
        }
    }

    private static void swapSort_2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j])
                    swap(arr, j, j - 1);
            }
            //System.out.println("第"+i+"轮插入");
            // System.out.println(Arrays.toString(arr));
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void testMethod() {
        int[] arr1 = {101, 34, 119, 1, -1, 89, 45, 23, 33};
        moveSort(arr1);
        System.out.println("移动法排序结果 :"+Arrays.toString(arr1));
        int[] arr2 = {101, 34, 119, 1, -1, 89, 45, 23, 33};
        swapSort_1(arr2);
        System.out.println("交换法1排序结果:"+Arrays.toString(arr2));
        int[] arr3 = {101, 34, 119, 1, -1, 89, 45, 23, 33};
        swapSort_2(arr3);
        System.out.println("交换法2排序结果:"+Arrays.toString(arr3));
    }

    private static void testSpeed() {
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000);

        }
        long t1 = System.currentTimeMillis();
        moveSort(arr);
        long t2 = System.currentTimeMillis();
        System.out.println("移动法所耗时间:" + (t2 - t1)+" ms");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000);

        }
        t1 = System.currentTimeMillis();
        swapSort_2(arr);
        t2 = System.currentTimeMillis();
        System.out.println("交换法毫秒数:" + (t2 - t1)+" ms");
    }
}
