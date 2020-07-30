//你将得到一个字符串数组 A。 
//
// 每次移动都可以交换 S 的任意两个偶数下标的字符或任意两个奇数下标的字符。 
//
// 如果经过任意次数的移动，S == T，那么两个字符串 S 和 T 是 特殊等价 的。 
//
// 例如，S = "zzxy" 和 T = "xyzz" 是一对特殊等价字符串，因为可以先交换 S[0] 和 S[2]，然后交换 S[1] 和 S[3]，使得
// "zzxy" -> "xzzy" -> "xyzz" 。 
//
// 现在规定，A 的 一组特殊等价字符串 就是 A 的一个同时满足下述条件的非空子集： 
//
// 
// 该组中的每一对字符串都是 特殊等价 的 
// 该组字符串已经涵盖了该类别中的所有特殊等价字符串，容量达到理论上的最大值
// （也就是说，如果一个字符串不在该组中，那么这个字符串就 不会 与该组内任何字符串特
//殊等价） 
// 
//
// 返回 A 中特殊等价字符串组的数量。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
//输出：3
//解释：
//其中一组为 ["abcd", "cdab", "cbad"]，因为它们是成对的特殊等价字符串，且没有其他字符串与这些字符串特殊等价。
//另外两组分别是 ["xyzz", "zzxy"] 和 ["zzyx"]。特别需要注意的是，"zzxy" 不与 "zzyx" 特殊等价。
// 
//
// 示例 2： 
//
// 输入：["abc","acb","bac","bca","cab","cba"]
//输出：3
//解释：3 组 ["abc","cba"]，["acb","bca"]，["bac","cab"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 1000 
// 1 <= A[i].length <= 20 
// 所有 A[i] 都具有相同的长度。 
// 所有 A[i] 都只由小写字母组成。 
// 
// Related Topics 字符串 
// 👍 72 👎 0

//Java：特殊等价字符串组
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;

public class P893GroupsOfSpecialEquivalentStrings {
    public static void main(String[] args) {
        Solution solution = new P893GroupsOfSpecialEquivalentStrings().new Solution();
        // TO TEST
//        String[] str = {"abc", "acb", "bac", "bca", "cab", "cba"};
        String[] str = {"abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx"};
//        String[] str = {"couxuxaubw", "zsptcwcghr", "kkntvvhbcc", "nkhtcvvckb", "crcwhspgzt"};
        int res = solution.numSpecialEquivGroups(str);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numSpecialEquivGroups(String[] A) {
            HashSet<String> hashSet = new HashSet<>(32);
            for (String currentStr : A) {
                char[] evens = new char[currentStr.length()];
                char[] odds = new char[currentStr.length()];
                char[] chars = currentStr.toCharArray();
                int even = 0;
                int odd = 0;
                for (int i=0;i<chars.length;i++) {
                     if(i%2 == 0){
                         evens[even] = chars[i];
                         even++;
                     }else {
                         odds[odd] = chars[i];
                         odd++;
                     }
                }
                Arrays.sort(evens);
                Arrays.sort(odds);
                hashSet.add(new String(evens) + new String(odds));
            }
            return hashSet.size();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}