//给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。 
//
// 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。 
//
// 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈
//顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。） 
//
// 
//
// 示例 1： 
//
// 输入：R = 1, C = 2, r0 = 0, c0 = 0
//输出：[[0,0],[0,1]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
// 
//
// 示例 2： 
//
// 输入：R = 2, C = 2, r0 = 0, c0 = 1
//输出：[[0,1],[0,0],[1,1],[1,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
//[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
// 
//
// 示例 3： 
//
// 输入：R = 2, C = 3, r0 = 1, c0 = 2
//输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
//其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= R <= 100 
// 1 <= C <= 100 
// 0 <= r0 < R 
// 0 <= c0 < C 
// 
// Related Topics 排序 
// 👍 40 👎 0

//题目编号：1030
package leetcode.editor.cn;

import java.util.*;

//Java：距离顺序排列矩阵单元格
public class P1030MatrixCellsInDistanceOrder {
    public static void main(String[] args) {
        Solution solution = new P1030MatrixCellsInDistanceOrder().new Solution();
        // TO TEST
//        int[][] res = solution.allCellsDistOrder(2, 3, 1, 2);
//        int[][] res = solution.allCellsDistOrder(1, 2, 0, 0);
        int[][] res = solution.allCellsDistOrder(4, 4, 1, 0);
        for (int[] re : res) {
            System.out.println("(" + re[0] + "," + re[1] + ")");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            int[][] res = new int[R * C][2];
            TreeMap<Integer, List<int[]>> map = new TreeMap<>();
            for (int rPos = 0; rPos < R; rPos++) {
                for (int cPos = 0; cPos < C; cPos++) {
                    int[] currentPoint = {rPos, cPos};
                    int currentDistance = Math.abs(rPos - r0) + Math.abs(cPos - c0);
                    if (map.get(currentDistance) == null) {
                        List<int[]> list = new ArrayList<>();
                        list.add(currentPoint);
                        map.put(currentDistance, list);
                    } else {
                        List<int[]> list = map.get(currentDistance);
                        list.add(currentPoint);
                        map.put(currentDistance, list);
                    }
                }
            }
            Iterator<Map.Entry<Integer, List<int[]>>> iterator = map.entrySet().iterator();
            int count = 0;
            while (iterator.hasNext()) {
                List<int[]> points = iterator.next().getValue();
                for (int[] point : points) {
                    res[count] = point;
                    count++;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}