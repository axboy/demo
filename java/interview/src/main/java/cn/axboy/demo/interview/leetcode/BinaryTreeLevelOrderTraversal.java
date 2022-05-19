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
            List<Integer> level = new ArrayList<>();        //本层的结果数组
            int curSize = queue.size();                     //本层的元素数量
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
