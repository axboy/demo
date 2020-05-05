package cn.axboy.demo.interview.leetcode;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/27 00:50
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * 搜索旋转排序数组
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length);
    }

    //search [l, r)
    public int search(int[] nums, int target, int l, int r) {
        //2346781

        if (r - l == 0) {
            //找不到
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (target < nums[mid]) {
            if (nums[l] < nums[mid]) {
                //3456712 -> 4,1 左有序
                if (nums[l] <= target) {
                    return search(nums, target, l, mid);
                } else {
                    return search(nums, target, mid + 1, r);
                }
            } else {
                //9345678 -> 3 左无序
                return search(nums, target, l, mid);
            }
        } else {
            if (nums[r - 1] >= nums[mid]) {
                //9125678 -> 9,7 右有序
                if (nums[r - 1] < target) {
                    return search(nums, target, l, mid);
                } else {
                    return search(nums, target, mid + 1, r);
                }
            } else {
                //3467812 -> 8
                return search(nums, target, mid + 1, r);
            }
        }
        //throw can not reach here.
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray demo = new SearchInRotatedSortedArray();
        System.out.println(demo.search(new int[]{3, 4, 5, 6, 7, 1, 2}, 7));
        System.out.println(demo.search(new int[]{1, 2, 3, 4, 5, 6, 7}, 6));
        System.out.println(demo.search(new int[]{6, 7, 1, 2, 3, 4, 5}, 1));
        System.out.println(demo.search(new int[]{3, 1}, 3));
        System.out.println(demo.search(new int[]{3, 1, 2}, 2));
        System.out.println(demo.search(new int[]{2, 3, 4, 6, 7, 8, 1}, 1));
    }
}
