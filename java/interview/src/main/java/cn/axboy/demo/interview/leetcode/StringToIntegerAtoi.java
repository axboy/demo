package cn.axboy.demo.interview.leetcode;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/13 18:08
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class StringToIntegerAtoi {

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int idx = 0;
        int sign = 1;
        //移除前置空格
        while (idx < str.length() && str.charAt(idx) == ' ') {
            idx++;
        }
        //只有空格的情况
        if (idx == str.length()) {
            return 0;
        }
        //标记符号
        if (str.charAt(idx) == '+' || str.charAt(idx) == '-') {
            if (str.charAt(idx) == '-') {
                sign = -1;
            }
            idx++;
        }
        int answer = 0;
        while (idx < str.length() && str.charAt(idx) >= '0' && str.charAt(idx) <= '9') {
            if ((Integer.MAX_VALUE - str.charAt(idx) + '0') / 10 < answer) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            answer = answer * 10 + (str.charAt(idx) - '0');
            idx++;
        }
        return answer * sign;
    }

    public static void main(String[] args) {
        StringToIntegerAtoi demo = new StringToIntegerAtoi();
        System.out.println(demo.myAtoi(""));
        System.out.println(demo.myAtoi("  "));
        System.out.println(demo.myAtoi("  asd1231"));
        System.out.println(demo.myAtoi("  +a"));
        System.out.println(demo.myAtoi("  +687687"));
        System.out.println(demo.myAtoi("  - 123532d"));
        System.out.println(demo.myAtoi("-91283472332a"));
        System.out.println(demo.myAtoi("2147483646"));
    }
}
