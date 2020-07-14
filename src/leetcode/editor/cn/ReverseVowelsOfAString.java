//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
//
// 示例 1: 
//
// 输入: "hello"
//输出: "holle"
// 
//
// 示例 2: 
//
// 输入: "leetcode"
//输出: "leotcede" 
//
// 说明: 
//元音字母不包含字母"y"。 
// Related Topics 双指针 字符串 
// 👍 100 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class ReverseVowelsOfAString {
    public static void main(String[] args) {
        Solution solution = new ReverseVowelsOfAString().new Solution();
//        String ans = solution.reverseVowels("hello");
        String ans = solution.reverseVowels("aA");
//        String ans = solution.reverseVowels("leetcode");
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseVowels(String s) {
            int i = 0;
            int j = s.length() - 1;
            char[] data = s.toCharArray();
            while (i < data.length && j > -1 && i < j) {
                char left = data[i];
                char right = data[j];
                if (left == right) {
                    i++;
                    j--;
                } else {
                    if (!isVowel(left)){
                        i++;
                        continue;
                    }
                    if(!isVowel(right)){
                        j--;
                        continue;
                    }
                    char temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                    i++;
                    j--;
                }
            }
            return new String(data);
        }

        private boolean isVowel(char s) {
            return s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u'
                    || s == 'A' || s == 'E' || s == 'I' || s == 'O' || s == 'U';
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}