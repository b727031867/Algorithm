package test;

import java.util.LinkedList;

/**
 * @author GXF
 * 2021/10/19
 */
class TreeNode {
    int val;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode right;
    TreeNode left;

    public static TreeNode getTreeNode(Integer[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(nums[0]);
        LinkedList<TreeNode> subTree = new LinkedList<>();
        subTree.push(head);
        for (int i = 1; i < nums.length; i++) {
            if (!subTree.isEmpty()) {
                TreeNode cur = subTree.pop();
                if (nums[i] != null) {
                    cur.left = new TreeNode(nums[i]);
                    subTree.add(cur.left);
                }
                i++;
                if (i >= nums.length) {
                    break;
                }
                if (nums[i] != null) {
                    cur.right = new TreeNode(nums[i]);
                    subTree.add(cur.right);
                }

            } else {
                break;
            }
        }
        return head;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb,this);
        return sb.substring(0,sb.length()-1) + " current node value : " + this.val;
    }

    private void print(StringBuilder sb,TreeNode root){
        if(root == null){
            return ;
        }
        print(sb,root.left);
        sb.append(root.val);
        sb.append(",");
        print(sb,root.right);
    }
}
