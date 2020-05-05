package cn.axboy.demo.interview.leetcode;

import java.math.BigInteger;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/14 12:21
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 */
public class AddTwoNumbers2 {

    public BigInteger listToNum(ListNode l) {
        BigInteger num = BigInteger.valueOf(0);
        while (true) {
            //num = num * 10 + l.val;
            num = num.multiply(BigInteger.TEN).add(BigInteger.valueOf(l.val));
            if (l.next == null) {
                break;
            }
            l = l.next;
        }
        return num;
    }

    public ListNode numToList(BigInteger num) {
        if (num.compareTo(BigInteger.ZERO) == 0) {
            return new ListNode(0);
        }
        ListNode last = null;
        while (num.compareTo(BigInteger.ZERO) > 0) {
            //int c = (int) (num % 10);
            int c = num.mod(BigInteger.TEN).intValue();
            ListNode cur = new ListNode(c);
            cur.next = last;
            last = cur;
            //num /= 10;
            num = num.divide(BigInteger.TEN);
        }
        return last;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger x = listToNum(l1);
        BigInteger y = listToNum(l2);
        return numToList(x.add(y));
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        AddTwoNumbers2 demo = new AddTwoNumbers2();
        ListNode l1 = demo.numToList(new BigInteger("999999999999999999999999999999999"));
        ListNode l2 = demo.numToList(new BigInteger("1"));
        System.out.println(demo.listToNum(demo.addTwoNumbers(l1, l2)));
    }
}


