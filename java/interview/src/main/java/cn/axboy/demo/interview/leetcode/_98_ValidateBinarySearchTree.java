package cn.axboy.demo.interview.leetcode;

//验证二叉搜索树，中序遍历
//https://leetcode-cn.com/problems/validate-binary-search-tree/
public class _98_ValidateBinarySearchTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public boolean isValidBST(TreeNode root) {
            return inOrder(root, new long[]{Long.MIN_VALUE});
        }

        //数组是传引用
        private boolean inOrder(TreeNode root, long[] last) {
            if (root == null) {
                return true;
            }
            return inOrder(root.left, last)
                    && last[0] < (last[0] = root.val)
                    && inOrder(root.right, last);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(8);

        Solution demo = new Solution();
        System.out.println(demo.isValidBST(root));
    }
}
