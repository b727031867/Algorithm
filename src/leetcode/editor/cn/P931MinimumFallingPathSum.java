//给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。 
//
// 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第
//一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1
//, col + 1) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
//输出：13
//解释：下面是两条和最小的下降路径，用加粗标注：
//[[2,1,3],      [[2,1,3],
// [6,5,4],       [6,5,4],
// [7,8,9]]       [7,8,9]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[-19,57],[-40,-5]]
//输出：-59
//解释：下面是一条和最小的下降路径，用加粗标注：
//[[-19,57],
// [-40,-5]]
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[-48]]
//输出：-48
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length 
// n == matrix[i].length 
// 1 <= n <= 100 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 97 👎 0

package leetcode.editor.cn;
//java:下降路径最小和
public class P931MinimumFallingPathSum{
    public static void main(String[] args){
        Solution solution = new P931MinimumFallingPathSum().new Solution();
//        int[][] test = new int[][]{{2,1,3},{6,5,4},{7,8,9}};
        int[][] test = new int[][]{{100,-42,-46,-41},{31,97,10,-10},{-58,-51,82,89},{51,81,69,-51}};
        int res = solution.minFallingPathSum(test);
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int res = Integer.MAX_VALUE;
        for(int i = 0 ; i < matrix[0].length ; i++){
            int curMin =  matrix[0][i];
            int pickRow = 0;
            int pickCol = i;
            while(pickRow < matrix[0].length - 1){
                if(pickCol == 0){
                    if(matrix[pickRow + 1][pickCol] > matrix[pickRow + 1][pickCol+1]){
                        pickRow++;
                        pickCol++;
                    }else{
                        pickRow++;
                    }
                }else if(pickCol == matrix[0].length - 1){
                    if(matrix[pickRow + 1][pickCol] > matrix[pickRow + 1][pickCol-1]){
                        pickRow++;
                        pickCol--;
                    }else{
                        pickRow++;
                    }
                }else{
                    if(matrix[pickRow + 1][pickCol] < matrix[pickRow + 1][pickCol+1] && matrix[pickRow + 1][pickCol] < matrix[pickRow + 1][pickCol-1]){
                        pickRow++;
                    }else if(matrix[pickRow + 1][pickCol+1] < matrix[pickRow + 1][pickCol] && matrix[pickRow + 1][pickCol+1] < matrix[pickRow + 1][pickCol-1]){
                        pickRow++;
                        pickCol++;
                    }else{
                        pickRow++;
                        pickCol--;
                    }
                }
                curMin += matrix[pickRow][pickCol];
            }
            res = Math.min(res,curMin);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
