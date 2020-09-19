//给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。 
//
// 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。 
//
// 你可以返回任何满足上述条件的数组作为答案。 
//
// 
//
// 示例： 
//
// 输入：[4,2,5,7]
//输出：[4,5,2,7]
//解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 20000 
// A.length % 2 == 0 
// 0 <= A[i] <= 1000 
// 
//
// 
// Related Topics 排序 数组 
// 👍 121 👎 0

//题目编号：922
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;

//Java：按奇偶排序数组 II
public class P922SortArrayByParityIi {
    public static void main(String[] args) {
        Solution solution = new P922SortArrayByParityIi().new Solution();
        // TO TEST
        int[] A = {4, 2, 5, 7};
        int[] res = solution.sortArrayByParityII(A);
        System.out.println(Arrays.toString(res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArrayByParityII(int[] A) {
            int[] odd = new int[A.length / 2];
            int[] even = new int[A.length / 2];
            Arrays.sort(A);
            int oddPos = 0;
            int evenPos = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] % 2 == 0) {
                    even[evenPos] = A[i];
                    evenPos++;
                } else {
                    odd[oddPos] = A[i];
                    oddPos++;
                }
            }
            oddPos--;
            evenPos--;
            int[] result = new int[A.length];
            for (int i = 0; i < result.length; i++) {
                if (i % 2 == 0) {
                    result[i] = even[evenPos];
                    evenPos--;
                } else {
                    result[i] = odd[oddPos];
                    oddPos--;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}