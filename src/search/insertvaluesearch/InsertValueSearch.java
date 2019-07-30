package search.insertvaluesearch;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 5));
    }

    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (value > midVal) {
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (value < midVal) {
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
