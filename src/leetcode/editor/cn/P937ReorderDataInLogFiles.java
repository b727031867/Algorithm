//你有一个日志数组 logs。每条日志都是以空格分隔的字串。 
//
// 对于每条日志，其第一个字为字母与数字混合的 标识符 ，除标识符之外的所有字为这一条日志的 内容 。 
//
// 
// 除标识符之外，所有字均由小写字母组成的，称为 字母日志 
// 除标识符之外，所有字均由数字组成的，称为 数字日志 
// 
//
// 题目所用数据保证每个日志在其标识符后面至少有一个字。 
//
// 请按下述规则将日志重新排序： 
//
// 
// 所有 字母日志 都排在 数字日志 之前。 
// 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序； 
// 数字日志 应该按原来的顺序排列。 
// 
//
// 返回日志的最终顺序。 
//
// 
//
// 示例 ： 
//
// 输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
//输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= logs.length <= 100 
// 3 <= logs[i].length <= 100 
// logs[i] 保证有一个标识符，并且标识符后面有一个字。 
// 
// Related Topics 字符串 
// 👍 38 👎 0

//Java：重新排列日志文件
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P937ReorderDataInLogFiles {
    public static void main(String[] args) {
        Solution solution = new P937ReorderDataInLogFiles().new Solution();
        // TO TEST
        String[] logs = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
        String[] strings = solution.reorderLogFiles(logs);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String[] reorderLogFiles(String[] logs) {
            List<String> numArray = new ArrayList<>(logs.length);
            List<String> charArray = new ArrayList<>(128);
            for (String log : logs) {
                int pos = log.indexOf(' ');
                if (isNumber(log, pos)) {
                    numArray.add(log);
                } else {
                    charArray.add(log.substring(pos+1) + " " + log.substring(0, pos));
                }
            }
            //排序
            String[] left = charArray.toArray(new String[0]);
            Arrays.sort(left);
            //还原
            for (int i=0;i<left.length;i++) {
                int pos = left[i].lastIndexOf(' ');
                left[i] = left[i].substring(pos+1) + " " + left[i].substring(0, pos);
            }
            //拼接
            List<String> charList = new ArrayList<>(Arrays.asList(left));
            charList.addAll(numArray);
            return charList.toArray(new String[0]);
        }

        private boolean isNumber(String str, int pos) {
            for (int i = pos + 1; i < str.length(); i++) {
                char curChar = str.charAt(i);
                if (curChar == ' ') {
                    continue;
                }
                if (curChar < 48 || curChar > 57) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}