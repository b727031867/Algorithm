//给定一组字符，使用原地算法将其压缩。 
//
// 压缩后的长度必须始终小于或等于原数组长度。 
//
// 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。 
//
// 在完成原地修改输入数组后，返回数组的新长度。 
//
// 
//
// 进阶： 
//你能否仅使用O(1) 空间解决问题？ 
//
// 
//
// 示例 1： 
//
// 输入：
//["a","a","b","b","c","c","c"]
//
//输出：
//返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
//
//说明：
//"aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
// 
//
// 示例 2： 
//
// 输入：
//["a"]
//
//输出：
//返回 1 ，输入数组的前 1 个字符应该是：["a"]
//
//解释：
//没有任何字符串被替代。
// 
//
// 示例 3： 
//
// 输入：
//["a","b","b","b","b","b","b","b","b","b","b","b","b"]
//
//输出：
//返回 4 ，输入数组的前4个字符应该是：["a","b","1","2"]。
//
//解释：
//由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
//注意每个数字在数组中都有它自己的位置。
// 
//
// 
//
// 提示： 
//
// 
// 所有字符都有一个ASCII值在[35, 126]区间内。 
// 1 <= len(chars) <= 1000。 
// 
// Related Topics 字符串 
// 👍 119 👎 0

package leetcode.editor.cn;

public class StringCompression {
    public static void main(String[] args) {
        Solution solution = new StringCompression().new Solution();
//        char[] data = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        char[] data = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
//        char[] data = {'a', 'a', 'a', 'b', 'b', 'a', 'a'};
//        char[] data = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int total = solution.compress(data);
        System.out.println(data);
        System.out.println(total);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int compress(char[] chars) {
            int length = 0;
            //计算每种字符的数量
            int[] charNum = new int[92];
            char prev = '\n';
            for (char c : chars) {
                if (charNum[c - 35] > 0) {
                    charNum[c - 35]++;
                } else {
                    charNum[c - 35] = 1;
                }
                //替换
                if (prev != c && prev != '\n') {
                    length++;
                    length += calculateDigits(charNum[prev - 35], chars, length);
                    charNum[prev - 35] = 0;
                    chars[length] = c;
                }
                prev = c;
            }
            //处理末尾或者全为某种字符的情况
            int total = charNum[chars[length] - 35];
            if (total > 0) {
                if (total == 1) {
                    length++;
                } else {
                    length++;
                    length += calculateDigits(total, chars, length);
                }
            }
            return length;
        }

        private int calculateDigits(int sum, char[] chars, int index) {
            //字符一个的时候不需要用数字描述
            if (sum > 1) {
                String sumStr = String.valueOf(sum);
                for (int i = 0; i < sumStr.length(); i++) {
                    //多出的字母替换成数字
                    chars[index + i] = sumStr.charAt(i);
                }
                return sumStr.length();
            }else {
                return 0;
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}