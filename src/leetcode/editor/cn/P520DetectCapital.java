//给定一个单词，你需要判断单词的大写使用是否正确。 
//
// 我们定义，在以下情况时，单词的大写用法是正确的： 
//
// 
// 全部字母都是大写，比如"USA"。 
// 单词中所有字母都不是大写，比如"leetcode"。 
// 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。 
// 
//
// 否则，我们定义这个单词没有正确使用大写字母。 
//
// 示例 1: 
//
// 
//输入: "USA"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "FlaG"
//输出: False
// 
//
// 注意: 输入是由大写和小写拉丁字母组成的非空单词。 
// Related Topics 字符串 
// 👍 97 👎 0

package leetcode.editor.cn;

//Java：检测大写字母
public class P520DetectCapital {
    public static void main(String[] args) {
        Solution solution = new P520DetectCapital().new Solution();
        boolean res = solution.detectCapitalUse("fffF");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean detectCapitalUse(String word) {
            //大写字符的数量
            int isUpperCaseNum = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) - 'a' < 0) {
                    isUpperCaseNum++;
                }
            }
            //全为大写或小写
            if (isUpperCaseNum == word.length() || isUpperCaseNum == 0) {
                return true;
            }
            //第一位是大写，并且只有一位字符大写
            return isUpperCaseNum == 1 && word.charAt(0) - 'A' >= 0 && word.charAt(0) - 'A' < 32;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}