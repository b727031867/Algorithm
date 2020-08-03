//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串 
// 👍 54 👎 0

//Java：仅仅反转字母
package leetcode.editor.cn;

public class P917ReverseOnlyLetters {
    public static void main(String[] args) {
        Solution solution = new P917ReverseOnlyLetters().new Solution();
        // TO TEST
//        String res = solution.reverseOnlyLetters("a-bC-dEf-ghIj");
//        String res = solution.reverseOnlyLetters("Test1ng-Leet=code-Q!");
        String res = solution.reverseOnlyLetters("ab-cd");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseOnlyLetters(String S) {
            int left = 0;
            int right = S.length()-1;
            char[] res = S.toCharArray();
            while (left < right) {
                if (isLetter(res[left]) && isLetter(res[right])) {
                    char temp = res[left];
                    res[left] = res[right];
                    res[right] = temp;
                    left++;
                    right--;
                }
                if(!isLetter(res[left])){
                    left++;
                }
                if(!isLetter(res[right])){
                    right--;
                }
            }
            return new String(res);
        }

        private boolean isLetter(char c) {
            return (c >= 65 && c < 91) || (c >= 97 && c < 123);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}