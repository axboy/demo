package cn.axboy.demo.interview.leetcode;

//区间和检索，线段树
//https://leetcode-cn.com/problems/range-sum-query-mutable/description/
public class RangeSumQueryMutable {

    static class NumArray {

        private final int[] tree;
        private final int[] data;

        public NumArray(int[] nums) {
            data = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            //对于元素个数为2的n次幂，比如length == 8，tree 只要 2 * length 即可。
            //  eg: 满二叉树，最后一层8个元素，即总的需要8 * 2
            //对于元素为非2的n次幂，比如length == 9，tree则需要 2 * 16(下一个整数次幂)的长度。参考HashMap的 tableSizeFor方法
            //  eg: 简单考虑，倒数第二层不超过9个元素，所以总数不会超过4 * length。
            tree = new int[nums.length * 4];
            if (nums.length > 0) {
                buildTree(0, 0, data.length - 1);
            }
        }

        //构建以index为根[l, r]的线段树
        private void buildTree(int index, int l, int r) {
            if (l == r) {
                tree[index] = data[l];
                return;
            }
            int left = lChild(index);
            int right = rChild(index);
            int mid = l + (r - l) / 2;

            buildTree(left, l, mid);
            buildTree(right, mid + 1, r);
            tree[index] = tree[left] + tree[right];
        }

        // 更新data[i]的值
        public void update(int i, int val) {
            data[i] = val;
            updateTree(0, 0, data.length - 1, i, val);
        }

        private void updateTree(int treeIndex, int l, int r, int targetIndex, int val) {
            if (l == r) {
                tree[treeIndex] = val;
                return;
            }
            int mid = l + (r - l) / 2;
            int left = lChild(treeIndex);
            int right = rChild(treeIndex);

            if (targetIndex > mid) {
                updateTree(right, mid + 1, r, targetIndex, val);
            } else {
                updateTree(left, l, mid, targetIndex, val);
            }
            tree[treeIndex] = tree[left] + tree[right];
        }

        //计算data[i,j]的和
        public int sumRange(int i, int j) {
            return queryTree(0, 0, data.length - 1, i, j);
        }

        //以treeIndex为根的线段树[l,r]范围中搜索[queryL,queryR]
        private int queryTree(int treeIndex, int l, int r, int queryL, int queryR) {
            if (l == queryL && r == queryR) {
                return tree[treeIndex];
            }
            int mid = l + (r - l) / 2;
            int left = lChild(treeIndex);
            int right = rChild(treeIndex);

            if (queryL >= mid + 1) {
                return queryTree(right, mid + 1, r, queryL, queryR);
            }
            if (queryR <= mid) {
                return queryTree(left, l, mid, queryL, queryR);
            }
            int leftResult = queryTree(left, l, mid, queryL, mid);
            int rightResult = queryTree(right, mid + 1, r, mid + 1, queryR);
            return leftResult + rightResult;
        }

        //获取左孩子索引
        private int lChild(int parent) {
            //2 * (p + 1) - 1
            return 2 * parent + 1;
        }

        //获取右孩子索引
        private int rChild(int parent) {
            //2 * (p + 1)
            //lChild + 1
            return 2 * parent + 2;
        }
    }

    public static void main(String[] args) {
        NumArray test = new NumArray(new int[]{9, -8});
        test.update(0, 3);
        test.sumRange(1, 1);
        test.sumRange(0, 1);
        test.update(1, -3);
        test.sumRange(0, 1);
    }
}
