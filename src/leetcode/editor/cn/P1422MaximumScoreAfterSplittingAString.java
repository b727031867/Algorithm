//给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。 
//
//
// 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。 
//
// 
//
// 示例 1： 
//
// 输入：s = "011101"
//输出：5 
//解释：
//将字符串 s 划分为两个非空子字符串的可行方案有：
//左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5 
//左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4 
//左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3 
//左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2 
//左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
// 
//
// 示例 2： 
//
// 输入：s = "00111"
//输出：5
//解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
// 
//
// 示例 3： 
//
// 输入：s = "1111"
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 2 <= s.length <= 500 
// 字符串 s 仅由字符 '0' 和 '1' 组成。 
// 
// Related Topics 字符串 
// 👍 15 👎 0

//题目编号：1422
package leetcode.editor.cn;

//Java：分割字符串的最大得分
public class P1422MaximumScoreAfterSplittingAString {
    public static void main(String[] args) {
        Solution solution = new P1422MaximumScoreAfterSplittingAString().new Solution();
        // TO TEST
        String s = "00";
        int res = solution.maxScore(s);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxScore(String s) {
            int max = 0;
            int prev = 0;
            for (int i = 1; i < s.length(); i++) {
                int total;
                //计算0
                total = countZero(s,prev);
                //计算1
                total += countOne(s,i);
                if(total > max){
                    max = total;
                }
                prev = i;
            }
            return max;
        }

        private int countOne(String s, int pos) {
            int sum = 0;
            for (int i = pos; i <s.length(); i++) {
                if(s.charAt(i) == '1'){
                    sum++;
                }
            }
            return sum;
        }

        private int countZero(String s,int pos){
            int sum = 0;
            for (int i = pos; i >= 0; i--) {
                if(s.charAt(i) == '0'){
                    sum++;
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}