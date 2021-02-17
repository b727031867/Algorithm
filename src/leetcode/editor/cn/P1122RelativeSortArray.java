//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 79 👎 0

//题目编号：1122
package leetcode.editor.cn;

import java.util.Arrays;

//Java：数组的相对排序
public class P1122RelativeSortArray {
    public static void main(String[] args) {
        Solution solution = new P1122RelativeSortArray().new Solution();
        // TO TEST
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] res = solution.relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            Arrays.sort(arr1);
            int[] res = new int[arr1.length];
            int total = 0;
            for (int value : arr2) {
                //寻找当前数组是否存在该数字
                for (int i = 0; i < arr1.length; i++) {
                    if (arr1[i] == value) {
                        res[total] = value;
                        arr1[i] = Integer.MIN_VALUE;
                        total++;
                    } else if (value < arr1[i]) {
                        break;
                    }
                }
            }
            Arrays.sort(arr1);
            //合并剩余数组
            if (arr1.length - total >= 0) System.arraycopy(arr1, total, res, total, arr1.length - total);
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}