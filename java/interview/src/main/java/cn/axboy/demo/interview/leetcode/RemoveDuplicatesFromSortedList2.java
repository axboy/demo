package cn.axboy.demo.interview.leetcode;

//删除排序链表中的重复元素 II
//https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
public class RemoveDuplicatesFromSortedList2 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        ListNode newNode = null;
        boolean skip = false;
        for (; head.next != null; head = head.next) {
            if (head.val == head.next.val) {
                skip = true;
                continue;
            }
            if (skip) {
                skip = false;
                continue;
            }
            if (newHead == null) {
                newHead = new ListNode(head.val);
                newNode = newHead;
                continue;
            }
            newNode.next = new ListNode(head.val);
            newNode = newNode.next;
        }
        if (!skip) {
            if (newHead == null) {
                return new ListNode(head.val);
            }
            newNode.next = new ListNode(head.val);
        }
        return newHead;
    }
}
