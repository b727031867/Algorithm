//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1423 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//java:电话号码的字母组合
public class P17LetterCombinationsOfAPhoneNumber{
    public static void main(String[] args){
        Solution solution = new P17LetterCombinationsOfAPhoneNumber().new Solution();
        List<String> res = solution.letterCombinations("23");
        for (String re : res) {
            System.out.println(re);
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        String [] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();
        String per = "";
        public List<String> letterCombinations(String digits) {
            if(digits.length() == 0){
                return res;
            }
            backTracked(0,digits);
            return res;
        }

        private void backTracked(int startPos, String digits){
            // 相等表示每位数都选了一个字母
            if(per.length() == digits.length()){
                res.add(per);
                return ;
            }
            // 字符串全部遍历完毕 则返回
            if(startPos >= digits.length()){
                return ;
            }
            char curNum =  digits.charAt(startPos);
            String ceil = map[curNum - 48];
            for(int i = 0 ; i < ceil.length() ; i++){
                char cur =  ceil.charAt(i);
                per = per + cur;
                backTracked(startPos + 1,digits);
                //回溯 去掉最后一位
                per = per.substring(0,per.length()-1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
