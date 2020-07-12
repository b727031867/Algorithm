//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1146 👎 0


package leetcode.editor.cn;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        String[] test = {"flower","flow","flight"};
        final String s = solution.longestCommonPrefix(test);
        System.out.println(s);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length <= 0) {
                return "";
            }
            //把第一个字符串作为默认前缀
            String res = strs[0];
            for (int i = 1; i < strs.length; i++) {
                int a = 0;
                //让前缀与每一个字符串比对
                while (a < res.length() && a < strs[i].length()) {
                    if (res.charAt(a) != strs[i].charAt(a)) {
                        break;
                    }
                    a++;
                }
                //截取当前的前缀字符串与对比字符串的最大相同部分，作为下一次比对的前缀字符串
                res = res.substring(0, a);
                //如果被比对的字符串没有任何与前缀相同的字符，则没有公共前缀
                if("".equals(res) || a == 0){
                    return "";
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}