//给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。请你按 任意 顺序返回 words 中是其他单词的子字符串的所有单词。 
//
// 如果你可以删除 words[j] 最左侧和/或最右侧的若干字符得到 word[i] ，那么字符串 words[i] 就是 words[j] 的一个子字符串
//。 
//
// 
//
// 示例 1： 
//
// 输入：words = ["mass","as","hero","superhero"]
//输出：["as","hero"]
//解释："as" 是 "mass" 的子字符串，"hero" 是 "superhero" 的子字符串。
//["hero","as"] 也是有效的答案。
// 
//
// 示例 2： 
//
// 输入：words = ["leetcode","et","code"]
//输出：["et","code"]
//解释："et" 和 "code" 都是 "leetcode" 的子字符串。
// 
//
// 示例 3： 
//
// 输入：words = ["blue","green","bu"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 100 
// 1 <= words[i].length <= 30 
// words[i] 仅包含小写英文字母。 
// 题目数据 保证 每个 words[i] 都是独一无二的。 
// 
// Related Topics 字符串 
// 👍 8 👎 0

//Java：数组中的字符串匹配
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P1408StringMatchingInAnArray {
    public static void main(String[] args) {
        Solution solution = new P1408StringMatchingInAnArray().new Solution();
        // TO TEST
//        String[] str = {"leetcode", "et", "code"};
//        String[] str = {"blue","green","bu"};
        String[] str = {"mass","as","hero","superhero"};
        List<String> res = solution.stringMatching(str);
        for (String re : res) {
            System.out.println(re);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> stringMatching(String[] words) {
            List<String> res = new ArrayList<>(words.length);
            Arrays.sort(words, Comparator.comparingInt(String::length));

            for (int i = 0; i < words.length; i++) {
                for (int j = i; j < words.length; j++) {
                    if (!words[j].equals(words[i]) && words[j].contains(words[i])) {
                        res.add(words[i]);
                        break;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}