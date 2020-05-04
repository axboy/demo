package cn.axboy.demo.interview.leetcode;

//颜色分类，改版的三路快排
//https://leetcode-cn.com/problems/sort-colors/
public class _75_SortColors {
    public void sortColors(int[] nums) {
        int p = 0;                  //最后一个0的索引
        int l = 0;                  //从左遍历
        int r = nums.length - 1;    //从右遍历
        while (l <= r) {
            if (nums[l] == 0) {
                if (p == l) {
                    l++;
                    p++;
                } else {
                    //000111022
                    swap(nums, l++, p++);
                }
                continue;
            }
            if (nums[r] == 2) {
                r--;
                continue;
            }
            if (nums[l] == 2) {
                swap(nums, r--, l);
                continue;
            }
            l++;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        _75_SortColors test = new _75_SortColors();
        int[] arr = new int[]{2, 0, 2, 1, 1, 0};
        test.sortColors(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
