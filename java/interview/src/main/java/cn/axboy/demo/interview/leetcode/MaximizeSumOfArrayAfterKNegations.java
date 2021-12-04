package cn.axboy.demo.interview.leetcode;

//1005. K 次取反后最大化的数组和
//https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
public class MaximizeSumOfArrayAfterKNegations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        if (k <= 0) {
            return sum(nums);
        }
        sort(nums, k, 0, nums.length);
        return sum(nums, k);
    }

    //nums [l, r)
    private void sort(int[] nums, int k, int l, int r) {
        if (k < l) {
            return;
        }
        if (l == r) {
            return;
        }
        int m = l;
        for (int i = l + 1; i < r; i++) {
            if (nums[l] < nums[i]) {
                continue;
            }
            swap(nums, ++m, i);
        }
        swap(nums, l, m);
        sort(nums, k, l, m);
        sort(nums, k, m + 1, r);
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    private int sum(int[] nums, int k) {
        if (nums[0] > 0) {
            return k % 2 == 0 ? sum(nums) : sum(nums) - 2 * nums[0];
        }
        nums[0] *= -1;
        for (int i = 1; i < k && i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] *= -1;
                continue;
            }
            int min = Math.min(nums[i - 1], nums[i]);
            return (k - i) % 2 == 0 ? sum(nums) : sum(nums) - 2 * min;
        }
        return k > nums.length && (k - nums.length) % 2 != 0 ? sum(nums) - 2 * nums[nums.length - 1] : sum(nums);
    }

    private int sum(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result += nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MaximizeSumOfArrayAfterKNegations()
                .largestSumAfterKNegations(
                        //new int[]{14, -13, 8, 29, 28, -19, -19, -11, -19, -26, -25, 29, 8, -27, 9, -12, -23, 28, 14, -6, 4, -28, 21, -7, -19, -13, -5, -26, -10, 28, -22, -2, -24, 1, -11, -3, -25, -9, -9, -3, 5, 3, -20, 3, 8, 9, 4, 8, 6, 13, 22, 16, 9, -23, 6, 20, 12, 30, -18, -29, -5, 12, 2, -6, 2, 9, -1, 26, 22, 30, 15, 30, -16, -29, -3, 2, 19, -24, 27, -11, 15, -11, -18, -9, -1, -2, -2, 28, -4, 15, -8, -8, 0, 22, -28, -19, -12, 14, 9, -30},
                        new int[]{-4, -6, -7},
                        4));

    }
}
