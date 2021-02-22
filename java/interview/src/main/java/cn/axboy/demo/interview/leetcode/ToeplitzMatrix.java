package cn.axboy.demo.interview.leetcode;

// https://leetcode-cn.com/problems/toeplitz-matrix/
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return true;
        }
        int h = matrix.length;
        int w = matrix[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int nextI = i + 1;
                int nextJ = j + 1;
                if (nextI >= h || nextJ >= w) {
                    break;
                }
                if (matrix[i][j] != matrix[nextI][nextJ]) {
                    return false;
                }
            }
        }
        return true;
    }
}
