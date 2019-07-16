package sort.quicksort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        int[] arr1 = {-5, -9, -1, 0, -4, 11, 52, 33};
//        sort(arr1, 0, arr1.length - 1);
        quickSort(arr1,0,arr1.length-1);
        System.out.println(Arrays.toString(arr1));
    }

    private static void sort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            swap(arr, l, r);
            if (arr[l] == pivot)
                r--;
            if (arr[r] == pivot)
                l++;
        }
        if (l == r) {
            l++;
            r--;
        }
        if (left < r)
            sort(arr, left, r);
        if (right > l)
            sort(arr, l, right);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static int partition(int[] arr,int left,int right){
        int pivot=arr[left];

        while (left<right){
            while (left<right && arr[right]>=pivot){
                right--;
            }
            arr[left]=arr[right];
            while (left<right && arr[left]<=pivot){
                left++;
            }
            arr[right]=arr[left];
        }
        arr[left]=pivot;
        return left;
    }

    private static void quickSort(int [] arr,int left,int right) {
        int pivotIndex=0;
        if(left<right) {
            pivotIndex=partition(arr,left,right);
            quickSort(arr,left,pivotIndex-1);
            quickSort(arr,pivotIndex+1,right);
        }
    }
}
