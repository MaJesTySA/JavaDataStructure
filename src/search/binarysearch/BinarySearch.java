package search.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        System.out.println(binarySearch(arr, 0, arr.length, 1000));
    }

    public static List binarySearch(int[] arr, int left, int right, int value) {
        List<Integer> resList = new ArrayList<>();
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            resList.add(-1);
            return resList;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (value > midVal) {
            return binarySearch(arr, mid + 1, right, value);
        } else if (value < midVal) {
            return binarySearch(arr, left, mid - 1, value);
        } else {

            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != value) {
                    break;
                }
                resList.add(temp);
                temp--;
            }
            resList.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                resList.add(temp);
                temp++;
            }
            return resList;
        }
    }
}
