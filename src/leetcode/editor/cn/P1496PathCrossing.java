//给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，分别表示向北、向南、向东、向西移动一个单位。 
//
// 机器人从二维平面上的原点 (0, 0) 处开始出发，按 path 所指示的路径行走。 
//
// 如果路径在任何位置上出现相交的情况，也就是走到之前已经走过的位置，请返回 True ；否则，返回 False 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：path = "NES"
//输出：false 
//解释：该路径没有在任何位置相交。 
//
// 示例 2： 
//
// 
//
// 输入：path = "NESWW"
//输出：true
//解释：该路径经过原点两次。 
//
// 
//
// 提示： 
//
// 
// 1 <= path.length <= 10^4 
// path 仅由 {'N', 'S', 'E', 'W} 中的字符组成 
// 
// Related Topics 字符串 
// 👍 14 👎 0

//题目编号：1496
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//Java：判断路径是否相交
public class P1496PathCrossing {
    public static void main(String[] args) {
        Solution solution = new P1496PathCrossing().new Solution();
        // TO TEST
        System.out.println(solution.isPathCrossing("NNSWWEWSSESSWENNW"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPathCrossing(String path) {
            int x = 0;
            int y = 0;
            HashMap<String, Integer> map = new HashMap<>(path.length());
            map.put("00",1);
            for (int i = 0; i < path.length(); i++) {
                if (path.charAt(i) == 'N') {
                    y++;
                } else if (path.charAt(i) == 'S') {
                    y--;
                } else if (path.charAt(i) == 'W') {
                    x--;
                } else {
                    x++;
                }
                String currentPos = x + String.valueOf(y);
                if (map.containsKey(currentPos)) {
                    return true;
                } else {
                    map.put(currentPos, 1);
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}