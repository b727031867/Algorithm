//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串 
// 👍 431 👎 0


package leetcode.editor.cn;

public class AddBinary {
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();
        String ans = solution.addBinary("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101", "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011");
        if (ans.equals("110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000")) {
            System.out.println("回答正确");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {
            int distance = Math.abs(a.length() - b.length());
            //补齐位数
            if (a.length() > b.length()) {
                StringBuilder sb = new StringBuilder(b);
                for (int i = 0; i < distance; i++) {
                    sb.insert(0, "0");
                }
                b = sb.toString();
            } else {
                StringBuilder sb = new StringBuilder(a);
                for (int i = 0; i < distance; i++) {
                    sb.insert(0, "0");
                }
                a = sb.toString();
            }
            StringBuilder res = new StringBuilder();
            //上一位相加是否产生进位
            boolean flag = false;
            //模拟二进制进一
            for (int i = a.length() - 1; i > -1; i--) {
                if (a.charAt(i) == b.charAt(i)) {
                    if (a.charAt(i) == '0') {
                        //上一轮进位了
                        if (flag) {
                            res.insert(0, '1');
                            flag = false;
                        } else {
                            res.insert(0, '0');
                        }
                    } else {
                        if (flag) {
                            res.insert(0, '1');
                        } else {
                            res.insert(0, '0');
                        }
                        //进位
                        flag = true;
                    }
                } else {
                    //上一轮进位，这一轮会连着进位
                    if (flag) {
                        res.insert(0, '0');
                    } else {
                        res.insert(0, '1');
                    }
                }
            }
            //最后一位也进位
            if (flag) {
                res.insert(0, '1');
            }
            return res.toString();
        }

//        private long binaryToDecimal(String binaryStr) {
//            long sum = 0;
//            byte[] bytes = binaryStr.getBytes();
//            for (int i = 0; i < bytes.length; i++) {
//                sum += (bytes[i] - 48) << bytes.length - 1 - i;
//            }
//            return sum;
//        }
//
//        private String decimalToBinary(long decimal) {
//            StringBuilder sb = new StringBuilder();
//            while (decimal > 0) {
//                sb.insert(0, decimal % 2);
//                decimal = decimal / 2;
//            }
//            if ("".equals(sb.toString())) {
//                return "0";
//            }
//            return sb.toString();
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}