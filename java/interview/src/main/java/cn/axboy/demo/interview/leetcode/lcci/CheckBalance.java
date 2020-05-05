package cn.axboy.demo.interview.leetcode.lcci;

import cn.axboy.demo.interview.leetcode.TreeNode;

//检查平衡性
//https://leetcode-cn.com/problems/check-balance-lcci/
public class CheckBalance {

    public boolean isBalanced(TreeNode root) {
        boolean[] ans = new boolean[]{true};
        height(root, ans);
        return ans[0];
    }

    //
    //       1
    //      / \
    //     2   2
    //    /     \
    //   3       3
    //  / \
    // 4   4
    // 获取以root为根的高度，若不平衡，提前退出
    private int height(TreeNode root, boolean[] ans) {
        if (!ans[0]) {
            return -1;
        }
        if (root == null) {
            return 0;
        }
        int hl = height(root.left, ans);
        int hr = height(root.right, ans);
        if (Math.abs(hl - hr) > 1) {
            ans[0] = false;
            return -1;
        }
        return Math.max(hl, hr) + 1;
    }
}
