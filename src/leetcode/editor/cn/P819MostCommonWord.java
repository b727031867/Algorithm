//给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。 
//
// 题目保证至少有一个词不在禁用列表中，而且答案唯一。 
//
// 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。 
//
// 
//
// 示例： 
//
// 输入: 
//paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
//banned = ["hit"]
//输出: "ball"
//解释: 
//"hit" 出现了3次，但它是一个禁用的单词。
//"ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。 
//注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"）， 
//"hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= 段落长度 <= 1000 
// 0 <= 禁用单词个数 <= 100 
// 1 <= 禁用单词长度 <= 10 
// 答案是唯一的, 且都是小写字母 (即使在 paragraph 里是大写的，即使是一些特定的名词，答案都是小写的。) 
// paragraph 只包含字母、空格和下列标点符号!?',;. 
// 不存在没有连字符或者带有连字符的单词。 
// 单词里只包含字母，不会出现省略号或者其他标点符号。 
// 
// Related Topics 字符串 
// 👍 65 👎 0

//Java：最常见的单词
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class P819MostCommonWord {
    public static void main(String[] args) {
        Solution solution = new P819MostCommonWord().new Solution();
        // TO TEST
        String[] band = {"hit"};
//        String res = solution.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.",band);
        String res = solution.mostCommonWord("Bob",band);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String mostCommonWord(String paragraph, String[] banned) {
            HashMap<String, Integer> worlds = new HashMap<>(banned.length);
            for (String s : banned) {
                worlds.put(s, -1);
            }
            int left = 0;
            int right = 1;
            int max=0;
            String res = "";
            while (right < paragraph.length()) {
                while (left < paragraph.length() && !isLegalChar(paragraph.charAt(left)) ) {
                    left++;
                }
                right = left + 1;
                while (right < paragraph.length() && isLegalChar(paragraph.charAt(right))) {
                    right++;
                }
                if(right > paragraph.length()){
                    break;
                }
                String world = paragraph.substring(left, right).toLowerCase();
                if (null == worlds.get(world)) {
                    worlds.put(world.toLowerCase(), 1);
                } else if (worlds.get(world.toLowerCase()) > 0) {
                    worlds.put(world.toLowerCase(), worlds.get(world.toLowerCase()) + 1);
                }
                if(worlds.get(world.toLowerCase())> max){
                    max = worlds.get(world.toLowerCase());
                    res = world.toLowerCase();
                }
                left = right;
            }
            return res;
        }

        private boolean isLegalChar(char c) {
            return (97 <= c && 123 > c) || (91 > c && 65 <= c);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}