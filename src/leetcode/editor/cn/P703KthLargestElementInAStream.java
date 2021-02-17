//设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。 
//
// 请实现 KthLargest 类： 
//
// 
// KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。 
// int add(int val) 返回当前数据流中第 k 大的元素。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["KthLargest", "add", "add", "add", "add", "add"]
//[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
//输出：
//[null, 4, 5, 5, 8, 8]
//
//解释：
//KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
//kthLargest.add(3);   // return 4
//kthLargest.add(5);   // return 5
//kthLargest.add(10);  // return 5
//kthLargest.add(9);   // return 8
//kthLargest.add(4);   // return 8
// 
//
// 
//提示：
//
// 
// 1 <= k <= 104 
// 0 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// -104 <= val <= 104 
// 最多调用 add 方法 104 次 
// 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
// 
// Related Topics 堆 设计 
// 👍 160 👎 0

//题目编号：703
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//Java：数据流中的第 K 大元素
public class P703KthLargestElementInAStream {
    public static void main(String[] args) {
        // TO TEST
        KthLargest k1 = new P703KthLargestElementInAStream().new KthLargest(3, new int[]{4, 5, 8, 2});
        KthLargest k2 = new P703KthLargestElementInAStream().new KthLargest(1, new int[]{});
        KthLargest k3 = new P703KthLargestElementInAStream().new KthLargest(2, new int[]{0});
        KthLargest k4 = new P703KthLargestElementInAStream().new KthLargest(3, new int[]{5, -1});
        P703KthLargestElementInAStream.test(k1, new int[]{3, 5, 10, 9, 4});
        P703KthLargestElementInAStream.test(k2, new int[]{-3, -2, -2, 0, 4});
        P703KthLargestElementInAStream.test(k3, new int[]{-1, 1, -2, -4, 3});
        P703KthLargestElementInAStream.test(k4, new int[]{2, 1, -1, 3, 4});
    }

    public static void test(KthLargest solution, int[] arr) {
        List<Integer> resList = new ArrayList<>(16);
        for (int value : arr) {
            resList.add(solution.add(value));
        }
        System.out.println(Arrays.toString(resList.toArray()));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class KthLargest {
        private int[] nums;
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.nums = new int[k];
            if (k <= nums.length) {
                Arrays.sort(nums);
                for (int i = nums.length - 1, j = 0; i >= 0 && j < k; i--, j++) {
                    this.nums[j] = nums[i];
                }
            } else {
                for (int i = 0; i < this.nums.length && i < nums.length; i++) {
                    this.nums[i] = nums[i];
                }
                for (int i = nums.length; i < this.nums.length; i++) {
                    this.nums[i] = Integer.MIN_VALUE;
                }
            }
            buildMinHeap();
        }

        public int add(int val) {
            //维护K个值的最小堆
            if (val > this.nums[0]) {
                this.nums[0] = val;
                buildMinHeap();
                int[] sort = sort(this.nums);
                return sort[k - 1];
            }else {
                return this.nums[0];
            }
        }

        private int[] sort(int[] arr) {
            int[] temp = new int[arr.length];
            System.arraycopy(arr, 0, temp, 0, arr.length);
            int length = arr.length;
            for (int i = temp.length - 1; i > 0; i--) {
                swap(temp, 0, i);
                length--;
                adjustHeap(temp, 0, length);
            }
            return temp;
        }

        private void buildMinHeap() {
            for (int i = nums.length / 2 - 1; i >= 0; i--) {
                adjustHeap(this.nums, i, this.nums.length);
            }
        }

        private void adjustHeap(int[] nums, int i, int length) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            if (left < length && nums[i] > nums[left]) {
                swap(nums, i, left);
                adjustHeap(nums, left, length);
            }
            if (right < length && nums[i] > nums[right]) {
                swap(nums, i, right);
                adjustHeap(nums, right, length);
            }
        }

        private void swap(int[] nums, int i, int k) {
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
//leetcode submit region end(Prohibit modification and deletion)

}