//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针 
// 👍 746 👎 0

//题目编号：18
package leetcode.editor.cn;

import java.util.*;

//Java：四数之和
public class P18FourSum {
    public static void main(String[] args) {
        Solution solution = new P18FourSum().new Solution();
        // TO TEST
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> lists = solution.fourSum(nums, target);
        for (List<Integer> list : lists) {
            list.forEach(System.out::print);
            System.out.println();
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>(8);
            if (nums.length < 4) {
                return res;
            }
            Arrays.sort(nums);
            int minValue = nums[0] + nums[1] + nums[2] + nums[3];
            int maxValue = nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] + nums[nums.length - 4];
            if (maxValue < target || minValue > target) {
                return res;
            }

            for (int i = 0; i < nums.length - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    int left = j + 1;
                    int right = nums.length - 1;
                    minValue = nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                    maxValue = nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1];
                    if (maxValue < target || minValue > target) {
                        left = right;
                    }
                    while (left < right) {
                        int temp = nums[i] + nums[j] + nums[left] + nums[right];
                        if (temp == target) {
                            List<Integer> integers = new ArrayList<>();
                            integers.add(nums[i]);
                            integers.add(nums[j]);
                            integers.add(nums[left]);
                            integers.add(nums[right]);
                            res.add(integers);
                            //跳过相同的值
                            while (left < right && nums[right] == nums[--right]) ;
                            while (left < right && nums[left] == nums[++left]) ;
                        } else if (temp > target) {
                            while (left < right && nums[right] == nums[--right]) ;
                        } else {
                            while (left < right && nums[left] == nums[++left]) ;
                        }
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}