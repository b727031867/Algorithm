//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 
// 👍 689 👎 0

//题目编号：16
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：最接近的三数之和
public class P16ThreeSumClosest{
    public static void main(String[] args) {
        Solution solution = new P16ThreeSumClosest().new Solution();
        // TO TEST
//        int[] test1 = {-4, -1, -1, 0, 1, 2};
//        int[] test2 = {-1,2,1,-4};
        int[] test3 = {0,1,2};
        int res = solution.threeSumClosest(test3, 0);
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        long res = Integer.MAX_VALUE;
        int l, r;
        for (int cur = 0; cur < nums.length - 2; cur++) {
            //跳过相同的值，相同的值查找和最接近的结果必然相同
            if (cur > 0 && nums[cur - 1] == nums[cur]) {
                continue;
            }
            //在当前位置的后半段查找，记录三者相加最接近target的数
            l = cur + 1;
            r = nums.length - 1;
            while (l < r) {
                int temp = nums[cur] + nums[l] + nums[r];
                res = Math.abs(target - res) >  Math.abs(target - temp) ? temp : res;
                //如果右边大，则右指针左移，减少和的值。如果移动后，和原来的值一样，则需要继续移动，跳过，无需重复计算，因为和不变，最接近的结果也不变
                if (temp > target) {
                    while (l < r && nums[r] == nums[--r]);
                } else if (temp < target) {
                    while (l < r && nums[l] == nums[++l]);
                } else {
                    return target;
                }
            }
        }
        return (int) res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}