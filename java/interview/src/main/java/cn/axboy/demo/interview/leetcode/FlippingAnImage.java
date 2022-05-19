package cn.axboy.demo.interview.leetcode;

// 翻转图像
// https://leetcode-cn.com/problems/flipping-an-image/
public class FlippingAnImage {

    public int[][] flipAndInvertImage(int[][] image) {
        for (int[] line : image) {
            int i = 0;
            int j = image[i].length - 1;
            while (i <= j) {
                int tmp = line[i] ^ 1;
                line[i] = line[j] ^ 1;
                line[j] = tmp;
                i++;
                j--;
            }
        }
        return image;
    }
}
