//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串 
// 👍 231 👎 0


package leetcode.editor.cn;

//Java：验证回文字符串 Ⅱ
public class P680ValidPalindromeIi {
    public static void main(String[] args) {
        Solution solution = new P680ValidPalindromeIi().new Solution();
        // TO TEST
        boolean res = solution.validPalindrome("aaaaaab");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //        超时
//        public boolean validPalindrome(String s) {
//            if(s.equals(new StringBuilder(s).reverse().toString())){
//                return true;
//            }
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < s.length(); i++) {
//                sb.append(s, 0, i);
//                sb.append(s,i+1,s.length());
//                if(sb.toString().equals(sb.reverse().toString())){
//                    return true;
//                }
//                sb.delete(0,sb.length());
//            }
//            return false;
//        }
        public boolean validPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    //左前进，看看是否可以形成回文
                    int leftTemp = left;
                    int rightTemp = right;
                    boolean leftRes = true;
                    for (left++; left < right;) {
                        if (s.charAt(left) == s.charAt(right)) {
                            left++;
                            right--;
                        }else {
                            leftRes = false;
                            break;
                        }
                    }
                    //右前进
                    boolean rightRes = true;
                    for (rightTemp--; leftTemp < rightTemp;) {
                        if (s.charAt(leftTemp) == s.charAt(rightTemp)) {
                            leftTemp++;
                            rightTemp--;
                        }else {
                            rightRes = false;
                            break;
                        }
                    }
                    return leftRes || rightRes;
                }
            }
            return true;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}