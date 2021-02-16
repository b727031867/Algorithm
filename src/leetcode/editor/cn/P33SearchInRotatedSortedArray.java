//升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
// 
//
// 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// nums 肯定会在某个点上旋转 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 二分查找 
// 👍 1179 👎 0

//题目编号：33
package leetcode.editor.cn;

import java.util.Arrays;

//Java：搜索旋转排序数组
public class P33SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new P33SearchInRotatedSortedArray().new Solution();
        // TO TEST
        int[] test1 = {4, 5, 6, 7, 0, 1, 2};
        int tar1 = 0;
        System.out.println(solution.search(test1, tar1));
        int[] test2 = {4, 5, 6, 7, 0, 1, 2};
        int tar2 = 3;
        System.out.println(solution.search(test2, tar2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            if (nums.length == 1) {
                return nums[0] == target ? 0 : -1;
            }
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    int[] leftArray = new int[i + 1];
                    System.arraycopy(nums, 0, leftArray, 0, i + 1);
                    int[] rightArray = new int[nums.length - i - 1];
                    System.arraycopy(nums, i + 1, rightArray, 0, nums.length - i - 1);
                    int left = Arrays.binarySearch(leftArray, target);
                    int right = Arrays.binarySearch(rightArray, target);
                    if (left > -1 && right < 0) {
                        return left;
                    } else if (left < 0 && right > -1) {
                        return right + leftArray.length;
                    } else {
                        return -1;
                    }
                }
            }
            int index = Arrays.binarySearch(nums, target);
            return Math.max(index, -1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}