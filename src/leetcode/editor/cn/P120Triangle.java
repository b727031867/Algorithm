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
// 👍 795 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//java:三角形最小路径和
public class P120Triangle{
    public static void main(String[] args){
        Solution solution = new P120Triangle().new Solution();
        //[[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(2);
        triangle.add(row1);

        List<Integer> row2 = new ArrayList<>();
        row2.add(3);
        row2.add(4);
        triangle.add(row2);

        List<Integer> row3 = new ArrayList<>();
        row3.add(6);
        row3.add(5);
        row3.add(7);
        triangle.add(row3);

        List<Integer> row4 = new ArrayList<>();
        row4.add(4);
        row4.add(1);
        row4.add(8);
        row4.add(3);
        triangle.add(row4);

        int res = solution.minimumTotal(triangle);
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            int [][] dp = new int[2][triangle.size()];
            for(int i = 0 ; i < triangle.size() ; i++){
                List<Integer> row = triangle.get(i);
                for(int j = 0 ; j < row.size() ; j++){
                    if(i % 2 == 0){
                        if(j == 0){
                            dp[0][j] = row.get(j) + dp[1][j];
                        }else if(j == row.size() - 1){
                            dp[0][j] = row.get(j) + dp[1][j - 1];
                        }else{
                            dp[0][j] = Math.min(dp[1][j - 1],dp[1][j]) + row.get(j);
                        }
                    }else{
                        if(j == 0){
                            dp[1][j] = row.get(j) + dp[0][j];
                        }else if(j == row.size() - 1){
                            dp[1][j] = row.get(j) + dp[0][j - 1];
                        }else{
                            dp[1][j] = Math.min(dp[0][j - 1],dp[0][j]) + row.get(j);
                        }
                    }

                }
            }
            int res = Integer.MAX_VALUE;
            int temp = (triangle.size() - 1) % 2;
            for(int i = 0 ; i < dp[temp].length ; i++){
                res = Math.min(dp[temp][i],res);
            }
            return res;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
