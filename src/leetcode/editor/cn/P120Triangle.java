//给定一个三角形 triangle ，找出自顶向下的最小路径和。 
//
// 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果
//正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//输出：11
//解释：如下面简图所示：
//   2
//  3 4
// 6 5 7
//4 1 8 3
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
// 
//
// 示例 2： 
//
// 
//输入：triangle = [[-10]]
//输出：-10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= triangle.length <= 200 
// triangle[0].length == 1 
// triangle[i].length == triangle[i - 1].length + 1 
// -104 <= triangle[i][j] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？ 
// 
// Related Topics 数组 动态规划 
// 👍 791 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//java:三角形最小路径和
public class P120Triangle{
    public static void main(String[] args){
        Solution solution = new P120Triangle().new Solution();
        List<List<Integer>> triangle = new ArrayList<>();

        List<Integer> row1 = new ArrayList<>();
        row1.add(-1);
        triangle.add(row1);

        List<Integer> row2 = new ArrayList<>();
        row2.add(2);
        row2.add(3);
        triangle.add(row2);

        List<Integer> row3 = new ArrayList<>();
        row3.add(1);
        row3.add(-1);
        row3.add(-3);
        triangle.add(row3);

//        List<Integer> row4 = new ArrayList<>();
//        row4.add(4);
//        row4.add(1);
//        row4.add(8);
//        row4.add(3);
//        triangle.add(row4);

        int res = solution.minimumTotal(triangle);

        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()  +  1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = triangle.get(0).get(0);
        for(int row = 2 ; row <= triangle.size(); row++){
            for(int i = 0 ; i < row - 1; i++){
                dp[row] = Math.min(dp[row],Math.min(triangle.get(row - 1).get(i + 1) + dp[row - 1],triangle.get(row - 1).get(i) + dp[row - 1]));
            }
        }
        return dp[triangle.size()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
