//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 2962 👎 0

//题目编号：15
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Java：三数之和
public class P15ThreeSum {

    public static void main(String[] args) {
        Solution solution = new P15ThreeSum().new Solution();
        // TO TEST
        int[] test1 = {-4, -1, -1, 0, 1, 2};
        int[] test2 = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        int[] test3 = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
//        List<List<Integer>> lists = solution.threeSum(test1);
//        List<List<Integer>> lists = solution.threeSum(test2);
        List<List<Integer>> lists = solution.threeSum(test3);
        for (List<Integer> list : lists) {
            Integer[] integers = new Integer[list.size()];
            System.out.println(Arrays.toString(list.toArray(integers)));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>(32);
            int l, r;
            for (int cur = 0; cur < nums.length - 2; cur++) {
                //当前值为正，则后续值必然为正，无法相加等于0
                if (nums[cur] > 0) {
                    break;
                }
                //跳过相同的值，相同的值查找和为零的结果必然相同
                if (cur > 0 && nums[cur - 1] == nums[cur]) {
                    continue;
                }
                //在当前位置的后半段查找，符合三者相加为0的数
                l = cur + 1;
                r = nums.length - 1;
                while (l < r) {
                    int temp = nums[cur] + nums[l] + nums[r];
                    //如果右边大，则右指针左移，减少和的值。如果移动后，和原来的值一样，则需要继续移动，跳过，防止添加重复结果
                    if (temp > 0) {
                        while (l < r && nums[r] == nums[--r]);
                    } else if (temp < 0) {
                        while (l < r && nums[l] == nums[++l]);
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[cur]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        res.add(list);
                        while (l < r && nums[r] == nums[--r]);
                        while (l < r && nums[l] == nums[++l]);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}