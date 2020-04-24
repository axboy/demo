package cn.axboy.demo.interview.leetcode;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/24 16:21
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * 数组中的逆序对，自底向上求解
 */
public class _51_ArrayReversePairs {

    public int reversePairs(int[] nums) {
        int ans = 0;
        for (int sz = 1; sz <= nums.length; sz += sz) {
            for (int l = 0; l <= nums.length; l += sz + sz) {
                int r = l + sz + sz;
                int mid = l + sz;
                if (r > nums.length) {
                    r = nums.length;
                }
                ans += merge(nums, l, mid, r);
            }
        }
        return ans;
    }

    //merge [l, mid) & [mid, r)
    private int merge(int[] nums, int l, int mid, int r) {
        int[] tmpArr = new int[r - l];
        for (int i = l; i < r; i++) {
            tmpArr[i - l] = nums[i];
        }
        int ans = 0;
        int i = l;
        int j = mid;
        int k = l;
        while (k < r) {
            if (i >= mid) {
                nums[k++] = tmpArr[j++ - l];
                continue;
            }
            if (j >= r) {
                nums[k++] = tmpArr[i++ - l];
                continue;
            }
            if (tmpArr[i - l] <= tmpArr[j - l]) {
                nums[k++] = tmpArr[i++ - l];
            } else {
                nums[k++] = tmpArr[j++ - l];
                ans += (mid - i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        _51_ArrayReversePairs demo = new _51_ArrayReversePairs();
        System.out.println(demo.reversePairs(new int[]{1, 3, 4, 2, 5}));
    }
}
