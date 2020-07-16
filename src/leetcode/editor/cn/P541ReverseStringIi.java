//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串 
// 👍 80 👎 0

package leetcode.editor.cn;

//Java：反转字符串 II
public class P541ReverseStringIi {
    public static void main(String[] args) {
        Solution solution = new P541ReverseStringIi().new Solution();
//        String res = solution.reverseStr("abcdefg", 3);
//        String res = solution.reverseStr("abcdefg", 1);
        String res = solution.reverseStr("krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc", 20);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseStr(String s, int k) {
            int pos = 0;
            StringBuilder res = new StringBuilder();
            if (k == 1) {
                return s;
            }
            if (k > s.length()) {
                return new StringBuilder(s).reverse().toString();
            }
            for (int i = 1; pos + 2 * k < s.length(); i++) {
                //反转前k位字符
                res.append(new StringBuilder(s.substring(pos, pos + k)).reverse());
                res.append(s, pos + k, pos + 2 * k);
                pos += (2 * k);
            }
            //处理剩余的字符
            if (s.length() - pos < k) {
                res.append(new StringBuilder(s.substring(pos)).reverse());
            } else {
                res.append(new StringBuilder(s.substring(pos, pos + k)).reverse());
                res.append(s, pos + k, s.length());
            }
            return res.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}