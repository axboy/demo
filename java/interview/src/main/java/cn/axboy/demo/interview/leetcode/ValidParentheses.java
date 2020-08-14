package cn.axboy.demo.interview.leetcode;

import java.util.Stack;

//有效的括号
//https://leetcode-cn.com/problems/valid-parentheses/
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        int i = 0;
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        while (i < arr.length) {
            switch (arr[i]) {
                case '(':
                case '[':
                case '{':
                    stack.push(arr[i++]);
                    continue;
                case ')':
                    if (stack.empty() || stack.pop() != '(') {
                        return false;
                    }
                    i++;
                    continue;
                case ']':
                    if (stack.empty() || stack.pop() != '[') {
                        return false;
                    }
                    i++;
                    continue;
                case '}':
                    if (stack.empty() || stack.pop() != '{') {
                        return false;
                    }
                    i++;
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        ValidParentheses test = new ValidParentheses();
        System.out.println(test.isValid("()"));
        System.out.println(test.isValid("()[]{}"));
        System.out.println(test.isValid("(]"));
        System.out.println(test.isValid("([)]"));
        System.out.println(test.isValid("{[]}"));
    }
}
