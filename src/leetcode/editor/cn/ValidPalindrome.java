//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man a plan a canal Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串 
// 👍 246 👎 0

package leetcode.editor.cn;

public class ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
//        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(solution.isPalindrome(" "));
        System.out.println(solution.isPalindrome("0P"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(String s) {
            boolean res = true;
            int i = 0;
            int j = s.length()-1;
            while (i < j) {
                //只有当i是数字或者字母的时候才进行移动比较
                if (!check(s.charAt(i))) {
                    i++;
                    continue;
                }
                if (!check(s.charAt(j))) {
                    j--;
                    continue;
                }
                String left = String.valueOf(s.charAt(i));
                String right = String.valueOf(s.charAt(j));
                if (!left.equalsIgnoreCase(right)) {
                    res = false;
                    break;
                }
                i++;
                j--;
            }
            return res;
        }

        /**
         * 检查是否是数字与字母
         *
         * @param ch 被检查的字符
         * @return 是否是数字或字母
         */
        private boolean check(char ch) {
            return ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z';
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}