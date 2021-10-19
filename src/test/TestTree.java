package test;

/**
 * @author 72703
 * 2021/10/19
 */
public class TestTree {
    public static void main(String[] args) {
        Integer[] tree = {2, null,1};
        TreeNode root = TreeNode.getTreeNode(tree);
        new Solution99().recoverTree(root);
        System.out.println(root);
    }
}

class Solution99 {
    private TreeNode firstErrorNode;
    private TreeNode secondErrorNode;
    private TreeNode prevNode;
    public void recoverTree(TreeNode root) {
        dfs(root);
        if(firstErrorNode != null && secondErrorNode != null){
            int temp = firstErrorNode.val;
            firstErrorNode.val = secondErrorNode.val;
            secondErrorNode.val = temp;
        }
    }

    private void dfs(TreeNode root){
        if(root == null){
            return ;
        }

        dfs(root.left);
        if(prevNode != null){
            if(prevNode.val > root.val){
                if(firstErrorNode == null){
                    firstErrorNode = prevNode;
                }
                secondErrorNode = root;
            }
        }
        prevNode = root;
        dfs(root.right);
    }

}
