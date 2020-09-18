//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 231 👎 0

//题目编号：349
package leetcode.editor.cn;

import java.util.*;

import static java.util.stream.Collectors.toSet;

//Java：两个数组的交集
public class P349IntersectionOfTwoArrays {
    public static void main(String[] args) {
        Solution solution = new P349IntersectionOfTwoArrays().new Solution();
        // TO TEST
//        int[] a = {1,2,2,1};
//        int[] b = {2,2};
//        int[] a = {4,9,5};
//        int[] b = {9,4,9,8,4};
//        int[] a = {8,0,3};
//        int[] b = {0,0};
        int[] a = {2, 1};
        int[] b = {1, 1};
        int[] res = solution.intersection(a, b);
        System.out.println(Arrays.toString(res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int[] result;
            if (nums1.length > nums2.length) {
                result = new int[nums1.length];
            } else {
                result = new int[nums2.length];
            }
            int left = 0;
            int right = 0;
            int pos = 0;
            while (left < nums1.length && right < nums2.length) {
                if (nums1[left] == nums2[right]) {
                    result[pos] = nums1[left];
                    left++;
                    right++;
                    pos++;
                } else if (nums1[left] > nums2[right]) {
                    right++;
                } else {
                    left++;
                }
                while (left >= 1 && left < nums1.length && nums1[left] == nums1[left - 1]) {
                    left++;
                }
                while (right >= 1 && right < nums2.length && nums2[right] == nums2[right - 1]) {
                    right++;
                }

            }
            return Arrays.copyOf(result,pos);
        }

        private int[] getInts(int[] nums1, int[] nums2) {
            int[] result;
            result = new int[nums2.length];
            Arrays.fill(result, Integer.MIN_VALUE);
            Arrays.sort(nums2);
            int total = 0;
            for (int value : nums1) {
                int index = Arrays.binarySearch(nums2, value);
                if (index > -1) {
                    boolean flag = false;
                    for (int val : result) {
                        if (val == value) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        result[total] = value;
                        total++;
                    }
                }
            }
            return Arrays.copyOf(result, total);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}