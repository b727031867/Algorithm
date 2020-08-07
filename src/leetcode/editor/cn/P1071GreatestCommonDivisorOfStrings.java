//对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。 
//
// 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。 
//
// 
//
// 示例 1： 
//
// 输入：str1 = "ABCABC", str2 = "ABC"
//输出："ABC"
// 
//
// 示例 2： 
//
// 输入：str1 = "ABABAB", str2 = "ABAB"
//输出："AB"
// 
//
// 示例 3： 
//
// 输入：str1 = "LEET", str2 = "CODE"
//输出：""
// 
//
// 
//
// 提示： 
//
// 
// 1 <= str1.length <= 1000 
// 1 <= str2.length <= 1000 
// str1[i] 和 str2[i] 为大写英文字母 
// 
// Related Topics 字符串 
// 👍 158 👎 0

//Java：字符串的最大公因子
package leetcode.editor.cn;

public class P1071GreatestCommonDivisorOfStrings {
    public static void main(String[] args) {
        Solution solution = new P1071GreatestCommonDivisorOfStrings().new Solution();
        // TO TEST
//        String res = solution.gcdOfStrings("ABABAB", "ABAB");
//        String res = solution.gcdOfStrings("LEET", "CODE");
//        String res = solution.gcdOfStrings("ABCABC", "ABC");
        String res = solution.gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String gcdOfStrings(String str1, String str2) {
            if (str1.equals(str2)) {
                return str1;
            }
            if (str1.length() > str2.length()) {
                for (int i = str2.length(); i > 0; i--) {
                    String temp = str1.substring(0, i);
                    if (isDivide(str1, temp) && isDivide(str2, temp)) {
                        return temp;
                    }
                }
            } else {
                for (int i = str1.length(); i > 0; i--) {
                    String temp = str1.substring(0, i);
                    if (isDivide(str2, temp) && isDivide(str1, temp)) {
                        return temp;
                    }
                }
            }
            return "";
        }

        /**
         * 判断字符串是否能被整除
         *
         * @param dividend 被除数
         * @param divisor  除数
         * @return 是否成功整除
         */
        private boolean isDivide(String dividend, String divisor) {
            if (dividend.length() % divisor.length() != 0) {
                return false;
            }
            for (int i = 0; i < dividend.length(); i += divisor.length()) {
                if (!dividend.substring(i, i + divisor.length()).equals(divisor)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}