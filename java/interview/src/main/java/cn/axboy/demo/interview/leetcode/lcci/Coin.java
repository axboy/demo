package cn.axboy.demo.interview.leetcode.lcci;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/23 12:13
 * https://leetcode-cn.com/problems/coin-lcci/
 */
public class Coin {

    //硬解，超时
    // n = 25 * a + 10 * b + 5 * c + d
    public int waysToChange(int n) {
        int result = 0;
        final int[] coins = new int[]{25, 10, 5, 1};
        final int mod = 1000000007;
        final int maxA = n / coins[0];
        for (int a = 0; a <= maxA; a++) {
            final int restA = n - coins[0] * a;
            final int maxB = restA / coins[1];
            for (int b = 0; b <= maxB; b++) {
                final int restB = restA - coins[1] * b;
                final int maxC = restB / coins[2];
                result += (maxC + 1);
                result %= mod;
            }
        }
        return result;
    }

    //动态规划
    public int waysToChange1(int n) {
        final int[] coins = new int[]{25, 10, 5, 1};
        final int mod = 1000000007;
        int[] arr = new int[n + 1];
        arr[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                arr[i] = (arr[i] + arr[i - coin]) % mod;
            }
        }
        return arr[n];
    }

    //数学求解
    public int waysToChange2(int n) {
        final int mod = 1000000007;
        int ans = 0;
        for (int i = 0; i * 25 <= n; i++) {
            int rest = n - i * 25;
            int maxA = rest / 10;
            int maxB = rest % 10 / 5;
            ans = ans + (int) ((long) (maxA + 1) * (maxA + maxB + 1) % mod);
            ans %= mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        Coin demo = new Coin();
        System.out.println(demo.waysToChange1(10));
        System.out.println(demo.waysToChange1(10));
        System.out.println(demo.waysToChange1(25));
        System.out.println(demo.waysToChange1(26));
        System.out.println(demo.waysToChange1(123151231));
        System.out.println(demo.waysToChange2(123151231));
    }
}
