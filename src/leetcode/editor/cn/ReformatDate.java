//给你一个字符串 date ，它的格式为 Day Month Year ，其中： 
//
// 
// Day 是集合 {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"} 中的一个元素。 
// Month 是集合 {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oc
//t", "Nov", "Dec"} 中的一个元素。 
// Year 的范围在 [1900, 2100] 之间。 
// 
//
// 请你将字符串转变为 YYYY-MM-DD 的格式，其中： 
//
// 
// YYYY 表示 4 位的年份。 
// MM 表示 2 位的月份。 
// DD 表示 2 位的天数。 
// 
//
// 
//
// 示例 1： 
//
// 输入：date = "20th Oct 2052"
//输出："2052-10-20"
// 
//
// 示例 2： 
//
// 输入：date = "6th Jun 1933"
//输出："1933-06-06"
// 
//
// 示例 3： 
//
// 输入：date = "26th May 1960"
//输出："1960-05-26"
// 
//
// 
//
// 提示： 
//
// 
// 给定日期保证是合法的，所以不需要处理异常输入。 
// 
// Related Topics 字符串 
// 👍 1 👎 0

package leetcode.editor.cn;

public class ReformatDate {
    public static void main(String[] args) {
        Solution solution = new ReformatDate().new Solution();
        System.out.println(solution.reformatDate("20th Oct 2052"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reformatDate(String date) {
            StringBuilder res = new StringBuilder();
            String[] datas = date.split(" ");
            String[] dates = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
            res.append(datas[2]);
            res.append("-");
            for (int i = 1; i <= dates.length; i++) {
                if (dates[i - 1].equals(datas[1])) {
                    if (i < 10) {
                        res.append("0");
                    }
                    res.append(i);
                    res.append("-");
                    break;
                }
            }
            int i = 0;
            char cur = datas[0].charAt(i);
            StringBuilder temp = new StringBuilder();
            while (cur != 't' && cur != 's' && cur != 'n' && cur != 'r' && i < datas.length) {
                temp.append(cur);
                i++;
                cur = datas[0].charAt(i);
            }
            if(Integer.parseInt(temp.toString()) < 10){
                res.append("0");
            }
            res.append(temp);
            return res.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}