//给你一个字符串 sentence 作为句子并指定检索词为 searchWord ，其中句子由若干用 单个空格 分隔的单词组成。 
//
// 请你检查检索词 searchWord 是否为句子 sentence 中任意单词的前缀。 
//
// 
// 如果 searchWord 是某一个单词的前缀，则返回句子 sentence 中该单词所对应的下标（下标从 1 开始）。 
// 如果 searchWord 是多个单词的前缀，则返回匹配的第一个单词的下标（最小下标）。 
// 如果 searchWord 不是任何单词的前缀，则返回 -1 。 
// 
//
// 字符串 S 的 「前缀」是 S 的任何前导连续子字符串。 
//
// 
//
// 示例 1： 
//
// 输入：sentence = "i love eating burger", searchWord = "burg"
//输出：4
//解释："burg" 是 "burger" 的前缀，而 "burger" 是句子中第 4 个单词。 
//
// 示例 2： 
//
// 输入：sentence = "this problem is an easy problem", searchWord = "pro"
//输出：2
//解释："pro" 是 "problem" 的前缀，而 "problem" 是句子中第 2 个也是第 6 个单词，但是应该返回最小下标 2 。
// 
//
// 示例 3： 
//
// 输入：sentence = "i am tired", searchWord = "you"
//输出：-1
//解释："you" 不是句子中任何单词的前缀。
// 
//
// 示例 4： 
//
// 输入：sentence = "i use triple pillow", searchWord = "pill"
//输出：4
// 
//
// 示例 5： 
//
// 输入：sentence = "hello from the other side", searchWord = "they"
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= sentence.length <= 100 
// 1 <= searchWord.length <= 10 
// sentence 由小写英文字母和空格组成。 
// searchWord 由小写英文字母组成。 
// 前缀就是紧密附着于词根的语素，中间不能插入其它成分，并且它的位置是固定的——-位于词根之前。（引用自 前缀_百度百科 ） 
// 
// Related Topics 字符串 
// 👍 10 👎 0

//题目编号：1455
package leetcode.editor.cn;

//Java：检查单词是否为句中其他单词的前缀
public class P1455CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {
    public static void main(String[] args) {
        Solution solution = new P1455CheckIfAWordOccursAsAPrefixOfAnyWordInASentence().new Solution();
        // TO TEST
        String se = "leetcode corona";
        String word = "leetco";
        System.out.println(solution.isPrefixOfWord(se, word));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int isPrefixOfWord(String sentence, String searchWord) {
            int res = 0;
            int sentencePos = 0;
            int searchWordPos = 0;
            sentence = sentence.trim();
            boolean hasPrefix = false;
            while (sentencePos < sentence.length() && searchWordPos < searchWord.length()) {
                if (sentence.charAt(sentencePos) == ' ') {
                    sentencePos++;
                    res++;
                    searchWordPos = 0;
                } else if (sentence.charAt(sentencePos) == searchWord.charAt(searchWordPos)) {
                    sentencePos++;
                    searchWordPos++;
                    if (searchWordPos >= searchWord.length()) {
                        hasPrefix = true;
                        break;
                    }
                } else {
                    while (sentencePos < sentence.length() && sentence.charAt(sentencePos) != ' ') {
                        sentencePos++;
                    }
                    searchWordPos = 0;
                }

            }
            if (!hasPrefix) {
                res = 0;
            } else if (res == 0) {
                return 1;
            }
            return res == 0 ? -1 : res + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}