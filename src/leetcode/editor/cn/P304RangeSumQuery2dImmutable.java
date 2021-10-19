//给定一个二维矩阵 matrix，以下类型的多个请求： 
//
// 
// 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。 
// 
//
// 实现 NumMatrix 类： 
//
// 
// NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化 
// int sumRegion(int row1, int col1, int row2, int col2) 返回左上角 (row1, col1) 、右下角
// (row2, col2) 的子矩阵的元素总和。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: 
//["NumMatrix","sumRegion","sumRegion","sumRegion"]
//[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,
//1,2,2],[1,2,2,4]]
//输出: 
//[null, 8, 11, 12]
//
//解释:
//NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,
//0,1,7],[1,0,3,0,5]]]);
//numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
//numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
//numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 200 
// -105 <= matrix[i][j] <= 105 
// 0 <= row1 <= row2 < m 
// 0 <= col1 <= col2 < n 
// 最多调用 104 次 sumRegion 方法 
// 
// Related Topics 设计 数组 矩阵 前缀和 
// 👍 280 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;

//java:二维区域和检索 - 矩阵不可变
public class P304RangeSumQuery2dImmutable{
    public static void main(String[] args){
        int [][] test = new int[][]{
                {3,0,1,4,2},
                {5,6,3,2,1},
                {1,2,0,1,5},
                {4,1,0,1,7},
                {1,0,3,0,5}
        };
        NumMatrix solution = new P304RangeSumQuery2dImmutable().new NumMatrix(test);
        int res1 = solution.sumRegion(2, 1, 4, 3);
        System.out.println(res1);
        int res2 = solution.sumRegion(1, 1, 2, 2);
        System.out.println(res2);
        int res3 = solution.sumRegion(1, 2, 2, 4);
        System.out.println(res3);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class NumMatrix {
    private int[][] matrix;
    private int[][] pre;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        int row = matrix.length;
        int col = matrix[0].length;
        this.pre = new int[row + 1][col + 1];
        for(int i = 1 ; i <= row ; i++){
            for(int j = 1 ; j <= col ; j++){
                pre[i][j] = pre[i][j-1] + matrix[i-1][j-1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int childRow = row2 - row1 + 1;
        int res = 0;
        for(int i = 0 ; i < childRow ; i++){
//            new LinkedList<>()
            res += pre[row2 + 1 - i][col2 + 1] - pre[row2 + 1 - i][col1 + 1] + matrix[row2 - i][col1];
        }
        return res;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
