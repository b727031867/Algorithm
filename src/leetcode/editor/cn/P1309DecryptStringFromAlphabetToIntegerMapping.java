//给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符： 
//
// 
// 字符（'a' - 'i'）分别用（'1' - '9'）表示。 
// 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。 
// 
//
// 返回映射之后形成的新字符串。 
//
// 题目数据保证映射始终唯一。 
//
// 
//
// 示例 1： 
//
// 输入：s = "10#11#12"
//输出："jkab"
//解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
// 
//
// 示例 2： 
//
// 输入：s = "1326#"
//输出："acz"
// 
//
// 示例 3： 
//
// 输入：s = "25#"
//输出："y"
// 
//
// 示例 4： 
//
// 输入：s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
//输出："abcdefghijklmnopqrstuvwxyz"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s[i] 只包含数字（'0'-'9'）和 '#' 字符。 
// s 是映射始终存在的有效字符串。 
// 
// Related Topics 字符串 
// 👍 29 👎 0

//Java：解码字母到整数映射
package leetcode.editor.cn;

import java.util.HashMap;

public class P1309DecryptStringFromAlphabetToIntegerMapping {
    public static void main(String[] args) {
        Solution solution = new P1309DecryptStringFromAlphabetToIntegerMapping().new Solution();
        // TO TEST
        String res = solution.freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#");
        String check = "abcdefghijklmnopqrstuvwxyz";
        if (res.equals(check)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String freqAlphabets(String s) {
            HashMap<String, String> data = new HashMap<>(32);
            data.put("0", "1");
            data.put("1", "a");
            data.put("2", "b");
            data.put("3", "c");
            data.put("4", "d");
            data.put("5", "e");
            data.put("6", "f");
            data.put("7", "g");
            data.put("8", "h");
            data.put("9", "i");
            data.put("10#", "j");
            data.put("11#", "k");
            data.put("12#", "l");
            data.put("13#", "m");
            data.put("14#", "n");
            data.put("15#", "o");
            data.put("16#", "p");
            data.put("17#", "q");
            data.put("18#", "r");
            data.put("19#", "s");
            data.put("20#", "t");
            data.put("21#", "u");
            data.put("22#", "v");
            data.put("23#", "w");
            data.put("24#", "x");
            data.put("25#", "y");
            data.put("26#", "z");
            StringBuilder sb = new StringBuilder();
            for (int i = s.length() - 1; i > -1; i--) {
                if (s.charAt(i) == '#') {
                    sb.insert(0, data.get(s.substring(i - 2, i + 1)));
                    i -= 2;
                } else {
                    sb.insert(0, data.get(String.valueOf(s.charAt(i))));
                }
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}