//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。 
//
// 注意： 
//
// 
// num1 和num2 的长度都小于 5100. 
// num1 和num2 都只包含数字 0-9. 
// num1 和num2 都不包含任何前导零。 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。 
// 
// Related Topics 字符串 
// 👍 179 👎 0

package leetcode.editor.cn;

public class AddStrings {
    public static void main(String[] args) {
        Solution solution = new AddStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addStrings(String num1, String num2) {
            if (num1 == null) {
                return num2;
            }
            if (num2 == null) {
                return num1;
            }
            StringBuilder res;
            if (num1.length() > num2.length()) {
                num2 = fillingZero(num2, num1.length() - num2.length());
                res = getAns(num1, num2);
            } else {
                num1 = fillingZero(num1, num2.length() - num1.length());
                res = getAns(num2, num1);
            }
            return res.toString();
        }

        private String fillingZero(String num, int total) {
            StringBuilder sb = new StringBuilder(num);
            for (int i = 0; i < total; i++) {
                sb.insert(0, '0');
            }
            return sb.toString();
        }

        /**
         * 模拟十进制运算，num1位数需要大于num2
         *
         * @param num1 被加数1
         * @param num2 加数2
         * @return 相加后的字符串Builder
         */
        private StringBuilder getAns(String num1, String num2) {
            int carry = 0;
            StringBuilder res = new StringBuilder();
            for (int i = num1.length() - 1; i > -1; i--) {
                //减去两倍的数字ASCII 96=48*2
                int digit = carry + (num1.charAt(i) + num2.charAt(i) - 96);
                if (digit > 9) {
                    carry = 1;
                    res.insert(0, (char) (digit - 10 + '0'));
                } else {
                    res.insert(0, (char) (digit + '0'));
                    carry = 0;
                }
            }
            if (carry != 0) {
                res.insert(0, '1');
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}