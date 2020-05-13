package cn.axboy.demo.interview.leetcode;

import java.util.*;

//二叉树层序遍历，逐层输出
//https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        queue.offer(root);
        while (queue.size() > 0) {
            List<Integer> level = new ArrayList<>();
            int curSize = queue.size();
            while (curSize-- > 0) {
                TreeNode n = queue.poll();
                level.add(n.val);
                if (n.left != null) {
                    queue.offer(n.left);
                }
                if (n.right != null) {
                    queue.offer(n.right);
                }
            }
            if (level.size() > 0) {
                ans.add(level);
            }
        }
        return ans;
    }
}
