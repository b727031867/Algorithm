package learn;

import java.util.Arrays;

/**
 * @author 龚秀峰
 * @version 1.0
 * @date 2020/10/24 20:52
 * 小顶堆及其排序
 */
public class MinHeap {
    public static void main(String[] args) {
        int[] initData = {1, 6, 15,3,22,6, 22, 13,0,100};
        MinHeap minHeap = new MinHeap();
        minHeap.buildMinHeap(initData);
        System.out.println(Arrays.toString(initData));
        int[] ints = minHeap.sortMinHeap(initData);
        System.out.println(Arrays.toString(ints));
    }

    //根据小顶堆，从大到小排序
    public int[] sortMinHeap(int[] arr){
        int length = arr.length;
        for (int i = arr.length - 1 ; i > 0; i--) {
            swap(arr,0,i);
            length--;
            adjustMinHeap(arr,0,length);
        }
        return arr;
    }
    
    public void buildMinHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustMinHeap(arr, i, arr.length);
        }
    }

    private void adjustMinHeap(int[] arr, int i, int length) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if(left<length && arr[i] > arr[left]){
            swap(arr,i,left);
            adjustMinHeap(arr,left,length);
        }
        if(right<length && arr[i] > arr[right]){
            swap(arr,i,right);
            adjustMinHeap(arr,right,length);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
