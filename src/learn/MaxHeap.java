package learn;

import java.util.Arrays;

/**
 * @author 龚秀峰
 * @version 1.0
 * @date 2020/10/24 11:01
 * 大顶堆及其排序
 */
public class MaxHeap {
    public static void main(String[] args) {
        int[] initData = {1, 6, 15,3,22,6, 22, 13,0,100};
        MaxHeap heap = new MaxHeap();
        heap.buildMaxHeap(initData);
        int[] ints = heap.sortHeap(initData);
        System.out.println(Arrays.toString(ints));
    }

    //大顶堆排序
    public int[] sortHeap(int[] arr) {
        int length = arr.length;
        for (int i = length - 1; i > 0; i--) {
            swap(arr, 0, i);
            //每次排序完毕，堆最后一个节点被踢出堆
            length--;
            adjustHeap(arr, 0, length);
        }
        return arr;
    }

    //构建大顶堆
    public void buildMaxHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
    }

    //构建大顶堆
    private void adjustHeap(int[] arr, int cur, int length) {
        int left = cur * 2 + 1;
        int right = cur * 2 + 2;
        if (left < length && arr[cur] < arr[left]) {
            swap(arr, cur, left);
            adjustHeap(arr, left, length);
        }
        if (right < length && arr[cur] < arr[right]) {
            swap(arr, cur, right);
            adjustHeap(arr, right, length);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
