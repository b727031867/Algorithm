//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
// 
//
// 示例 2: 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9] 
//
// 
//
// 说明： 
//
// 
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 
// 我们可以不考虑输出结果的顺序。 
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 393 👎 0

//题目编号：350
package leetcode.editor.cn;

import java.util.Arrays;

//Java：两个数组的交集 II
public class P350IntersectionOfTwoArraysIi{
    public static void main(String[] args) {
        Solution solution = new P350IntersectionOfTwoArraysIi().new Solution();
        // TO TEST
        //        int[] a = {1,2,2,1};
//        int[] b = {2,2};
//        int[] a = {4,9,5};
//        int[] b = {9,4,9,8,4};
//        int[] a = {8,0,3};
//        int[] b = {0,0};
        int[] a = {2, 1,1};
        int[] b = {1, 1};
        int[] res = solution.intersect(a, b);
        System.out.println(Arrays.toString(res));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
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
        }
        return Arrays.copyOf(result,pos);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}