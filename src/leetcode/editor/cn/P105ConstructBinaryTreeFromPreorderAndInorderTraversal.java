//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 996 👎 0

package leetcode.editor.cn;

import java.util.HashMap;

//java:从前序与中序遍历序列构造二叉树
public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal{
    public static void main(String[] args){
        Solution solution = new P105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        int [] pre1 = {};
        int [] in1 = {};
        solution.buildTree(pre1,in1);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
            TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;         this.left = left;
              this.right = right;
          }
}

class Solution {
    private HashMap<Integer, Integer> cache = new HashMap<>(32);
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            cache.put(inorder[i],i);
        }
        return build(preorder,inorder,0,preorder.length-1,0);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preLeftPos, int preRightPos, int inLeftPos) {
        if(preLeftPos > preRightPos){
            return null;
        }
        //中序遍历中根节点的位置
        Integer rootIndex = cache.get(preorder[preLeftPos]);
        //构建根节点
        TreeNode root = new TreeNode(inorder[rootIndex]);
        int leftTreeNum = rootIndex - inLeftPos;
        root.left = build(preorder,inorder,preLeftPos+1,preLeftPos+leftTreeNum,inLeftPos);
        root.right = build(preorder,inorder,preLeftPos+leftTreeNum+1,preRightPos,rootIndex+1);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
