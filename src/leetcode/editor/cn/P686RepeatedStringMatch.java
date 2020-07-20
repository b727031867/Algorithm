//给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。 
//
// 举个例子，A = "abcd"，B = "cdabcdab"。 
//
// 答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。 
//
//
// 注意: 
//
// A 与 B 字符串的长度在1和10000区间范围内。 
// Related Topics 字符串 
// 👍 92 👎 0


package leetcode.editor.cn;
//Java：重复叠加字符串匹配
public class P686RepeatedStringMatch{    
    public static void main(String[] args) {      
        Solution solution = new P686RepeatedStringMatch().new Solution();       
        // TO TEST
        int res = solution.repeatedStringMatch("abc", "cabcabca");
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder(A);
        int total = 0;
        if(B.length()%A.length() > 0){
            total = B.length()/A.length()+2;
        }else {
            total = B.length()/A.length()+1;
        }
        for(int i=1;i<=total;i++){
            if(sb.toString().contains(B)){
                return i;
            }
            sb.append(A);
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}