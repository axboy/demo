package cn.axboy.demo.interview.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/25 13:11
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        int[] path = new int[nums.length];
        dfs(nums, used, res, path, 0);
        return res;
    }

    private void dfs(int[] nums, boolean[] used, List<List<Integer>> res, int[] path, int depth) {
        if (nums.length == depth) {
            res.add(Arrays.stream(path).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path[depth] = nums[i];
            dfs(nums, used, res, path, depth + 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutations demo = new Permutations();
        List<List<Integer>> res = demo.permute(new int[]{1, 2, 3});
        System.out.println(res);
    }
}
