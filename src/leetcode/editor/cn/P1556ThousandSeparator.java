//给你一个整数 n，请你每隔三位添加点（即 "." 符号）作为千位分隔符，并将结果以字符串格式返回。 
//
// 
//
// 示例 1： 
//
// 输入：n = 987
//输出："987"
// 
//
// 示例 2： 
//
// 输入：n = 1234
//输出："1.234"
// 
//
// 示例 3： 
//
// 输入：n = 123456789
//输出："123.456.789"
// 
//
// 示例 4： 
//
// 输入：n = 0
//输出："0"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n < 2^31 
// 
// Related Topics 字符串 
// 👍 1 👎 0

//题目编号：1556
package leetcode.editor.cn;

//Java：千位分隔数
public class P1556ThousandSeparator {
    public static void main(String[] args) {
        Solution solution = new P1556ThousandSeparator().new Solution();
        // TO TEST
        System.out.println(solution.thousandSeparator(1234));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String thousandSeparator(int n) {
            StringBuilder sb = new StringBuilder();
            String nStr = String.valueOf(n);
            int num = 0;
            for (int i = nStr.length(); i > 0; i--) {
                if (num == 3 && i != nStr.length()) {
                    sb.insert(0, '.');
                    num = 0;
                }
                sb.insert(0, nStr.charAt(i - 1));
                num++;
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}