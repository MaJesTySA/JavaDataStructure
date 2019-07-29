package sort.heapsort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //升序排列，构建大顶堆
        int[] arr = {4, 6, 8, 5, 9, -1, 90, 89, 56, -999};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int temp;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 将一个数组（顺序存储二叉树），调整成一个大顶堆。
     * @param arr    待调整的数组
     * @param i      非叶子结点的索引
     * @param length 多少个元素调整成堆，逐渐减少
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        //先记录当前非叶子结点的值
        int temp = arr[i];
        //k是当前非叶子节点i的左节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //这里将左右两个子树值中的大值放到右边。
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果右子树的值比非叶子结点还大，需要将非叶子结点下沉。
            if (arr[k] > temp) {
                arr[i] = arr[k];
                //由于交换过后，原来的非叶子结点下沉，此时树的结构被破坏，
                //需要重新调整，所以i=k，以当前的k作为非叶子结点，继续调整。
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
