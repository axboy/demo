package cn.axboy.demo.interview.leetcode;

//区间检索，数组不可变
//https://leetcode-cn.com/problems/range-sum-query-immutable/submissions/
public class _303_RangeSumQueryImmutable {
    private static class NumArray {

        //sum[0], [0,0]的和
        //sum[i], [0,i]的和
        private final int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length];
            if (nums.length == 0) {
                return;
            }
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum[i] = nums[i] + sum[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            int r = sum[j];
            int l = i > 0 ? sum[i - 1] : 0;
            return r - l;
        }
    }

    public static void main(String[] args) {
        NumArray demo = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(demo.sumRange(0, 2));
        System.out.println(demo.sumRange(2, 5));
        System.out.println(demo.sumRange(0, 5));
    }
}
