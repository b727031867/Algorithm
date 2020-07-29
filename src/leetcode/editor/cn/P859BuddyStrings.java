//给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入： A = "ab", B = "ba"
//输出： true
// 
//
// 示例 2： 
//
// 输入： A = "ab", B = "ab"
//输出： false
// 
//
// 示例 3: 
//
// 输入： A = "aa", B = "aa"
//输出： true
// 
//
// 示例 4： 
//
// 输入： A = "aaaaaaabc", B = "aaaaaaacb"
//输出： true
// accdba A
// abcdca B
// 示例 5： 
//
// 输入： A = "", B = "aa"
//输出： false
// 
//
// 
//
// 提示： 
//
// 
// 0 <= A.length <= 20000 
// 0 <= B.length <= 20000 
// A 和 B 仅由小写字母构成。 
// 
// Related Topics 字符串 
// 👍 100 👎 0

//Java：亲密字符串
package leetcode.editor.cn;


import java.util.Arrays;
import java.util.HashMap;

public class P859BuddyStrings {
    public static void main(String[] args) {
        Solution solution = new P859BuddyStrings().new Solution();
        // TO TEST
//        boolean res = solution.buddyStrings("aaaaaaabc", "aaaaaaacb");
//        boolean res = solution.buddyStrings("aab", "aab");
//        boolean res = solution.buddyStrings("ab", "ab");
//        boolean res = solution.buddyStrings("", "ab");
//        boolean res = solution.buddyStrings("aa", "aa");
        boolean res = solution.buddyStrings("acccccb", "bccccca");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean buddyStrings(String A, String B) {
            //A B相等
            if(A.equals(B) && !"".equals(A)){
                HashMap<Character,Integer> map = new HashMap<>(A.length());
                int max = 0;
                for(int i=0;i<A.length();i++){
                    if(null != map.get(A.charAt(i))){
                        map.put(A.charAt(i),map.get(A.charAt(i))+1);
                        max = map.get(A.charAt(i));
                    }else {
                        map.put(A.charAt(i),1);
                    }
                    if(max >1){
                        return true;
                    }
                }
                return false;
            }
            if("".equals(A)){
                return false;
            }
            int posA = 0;
            int posB = 0;
            int firstDiffPos = 0;
            int secDiffPos = 0;
            int totalDiffNum = 0;
            while (posA < A.length() && posB < B.length()) {
                if (A.charAt(posA) != B.charAt(posB)) {
                    totalDiffNum++;
                    if(totalDiffNum == 1 ){
                        firstDiffPos = posA;
                    }else if(totalDiffNum ==2){
                        secDiffPos = posA;
                    }else {
                        return false;
                    }
                }
                posA++;
                posB++;
            }
            //进行交换
            char[] chars = A.toCharArray();
            char temp = chars[firstDiffPos];
            chars[firstDiffPos] = chars[secDiffPos];
            chars[secDiffPos] = temp;
            return new String(chars).equals(B);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}