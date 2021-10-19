//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 递归 链表 
// 👍 1201 👎 0

package leetcode.editor.cn;

//java:K 个一组翻转链表
public class P25ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new P25ReverseNodesInKGroup().new Solution();
        ListNode head = new ListNode(1);
        ListNode headCopy = head;
        for (int i = 2; i <= 5; i++) {
            head.next = new ListNode(i);
            head = head.next;
        }
        ListNode res = solution.reverseKGroup(headCopy, 2);
        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
        System.out.println("");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            int left = 0;
            int right = 0;
            ListNode headCopy = head;
            ListNode leftNode = headCopy;
            ListNode prevNode = null;
            while (headCopy != null) {
                right++;
                if (right - left >= k) {
                    head = reverseList(prevNode,leftNode, k);
                    prevNode = leftNode;
                    leftNode = headCopy;
                    left = right;
                }
                headCopy = headCopy.next;
            }
            return head;
        }

        public ListNode reverseList(ListNode prev,ListNode head,int k) {
            ListNode curr = head;
            String a = "a";
            
            while (curr != null && k >= 0) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                k--;
            }
            return prev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
