package search.fibsearch;

import java.util.Arrays;

public class FibSearch {
    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 19, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 1000));

    }

    private static int[] fib() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    //非递归
    public static int fibSearch(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int mid = 0;
        int[] fib = fib();
        //获取到fib分割下标k
        while (high > fib[k] - 1) {
            k++;
        }
        //因为f(k)的值可能大于arr的长度，需要构造一个新的数组
        int[] temp = Arrays.copyOf(arr, fib[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        while (low <= high) {
            mid = low + fib[k - 1] - 1;
            if (value < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (value > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high)
                    return mid;
                else
                    return high;
            }
        }
        return -1;
    }
}
