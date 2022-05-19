package cn.axboy.demo.interview.leetcode;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/24 16:21
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * 数组中的逆序对，自底向上求解
 */
public class ArrayReversePairs {

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
    //左右数组已有序
    private int merge(int[] nums, int l, int mid, int r) {
        //临时数组，拷贝[l,r)
        int[] tmpArr = new int[r - l];
        for (int i = l; i < r; i++) {
            tmpArr[i - l] = nums[i];
        }
        int ans = 0;
        int i = l;      //左数组索引，从l开始
        int j = mid;    //右数组索引，从mid开始
        int k = l;      //目标数组索引，从l开始
        while (k < r) {
            if (i >= mid) {
                //i >= mid，左数组已归并完成
                //结果直接取右边的，非逆序对，ans不处理
                nums[k++] = tmpArr[j++ - l];
                continue;
            }
            if (j >= r) {
                //j >= r，右边数组已归并完成
                //结果直接取左边的，非逆序对，ans不处理
                nums[k++] = tmpArr[i++ - l];
                continue;
            }
            if (tmpArr[i - l] <= tmpArr[j - l]) {
                //左侧<=右侧，选择左值，非逆序，ans不处理
                nums[k++] = tmpArr[i++ - l];
            } else {
                //左侧>右侧，选右值，和左侧剩余的值逆序
                nums[k++] = tmpArr[j++ - l];
                ans += (mid - i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ArrayReversePairs demo = new ArrayReversePairs();
        System.out.println(demo.reversePairs(new int[]{1, 3, 4, 2, 5}));
    }
}
