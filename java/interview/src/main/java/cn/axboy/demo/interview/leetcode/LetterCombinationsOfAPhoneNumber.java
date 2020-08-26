package cn.axboy.demo.interview.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {

    private static char[][] keyArr = new char[][]{
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>(digits.length() * 3);
        dfs(digits, result, "", 0);
        return result;
    }

    private void dfs(final String input,
                     final List<String> result,
                     String curResult,
                     int key) {
        if (key == input.length()) {
            if (curResult.length() > 0) {
                result.add(curResult);
            }
            return;
        }
        int keyIndex = input.charAt(key) - '1';
        if (keyArr[keyIndex].length == 0) {
            dfs(input, result, curResult, key + 1);
            return;
        }
        for (int j = 0; j < keyArr[keyIndex].length; j++) {
            dfs(input, result, curResult + keyArr[keyIndex][j], key + 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();
        System.out.println(test.letterCombinations("123"));
        System.out.println(test.letterCombinations("23"));
        System.out.println(test.letterCombinations("234"));
    }
}
