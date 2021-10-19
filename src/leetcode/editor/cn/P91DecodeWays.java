//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为： 
//
// 
// "AAJF" ，将消息分组为 (1 1 10 6) 
// "KJF" ，将消息分组为 (11 10 6) 
// 
//
// 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。 
//
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。 
//
// 题目数据保证答案肯定是一个 32 位 的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
// 
//
// 示例 4： 
//
// 
//输入：s = "06"
//输出：0
//解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 887 👎 0

package leetcode.editor.cn;
//java:解码方法
public class P91DecodeWays{
    public static void main(String[] args){
        Solution solution = new P91DecodeWays().new Solution();
        int res = solution.numDecodings("1217");
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if(n < 2){
            return 1;
        }
        int res = 0;
        int prev = 1;
        int prevPrev = 1;
        for(int i = 1 ; i < n ; i++){
            //当前解析总数只取决于 上两次解析的总数，分两种情况讨论
            res = 0;
            //无法解析则返回0
            if((s.charAt(i) - '0') == 0 && (s.charAt( i - 1) - '0') > 2
                    ||(s.charAt(i) - '0') == 0 && (s.charAt( i - 1) - '0') == 0
            ){
                return 0;
            }
            //新增的一位 直接翻译成 1~10 对应字母
            if(s.charAt(i) != '0'){
                res += prev;
            }
            //新增的一位和上一位组合翻译 成 10~26对应的字母
            if(s.charAt(i - 1) != '0' && (s.charAt(i) - '0') + (s.charAt( i - 1) - '0') * 10 < 27){
                res += prevPrev;
            }
            prevPrev = prev;
            prev = res;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
