package cn.axboy.demo.interview.leetcode;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/8/24 22:48
 * 重复的子字符串
 * https://leetcode-cn.com/problems/repeated-substring-pattern/
 */
public class RepeatedSubStringPattern {

    public boolean repeatedSubstringPattern(String s) {
        int halfLength = s.length() / 2;
        int length = s.length();
        for (int i = 1; i <= halfLength; i++) {
            if (length % i != 0) {
                continue;
            }
            boolean matched = true;
            for (int j = i; j < length; j++) {
                if (s.charAt(j) != s.charAt(j - i)) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                return true;
            }
        }
        return false;
    }
}
