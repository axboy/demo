package cn.axboy.demo.interview.leetcode;

import java.util.PriorityQueue;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/26 16:48
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * k路归并
 */
public class _23_MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        ListNode head = getNext(minHeap);
        ListNode last = head;
        while (minHeap.size() > 0) {
            ListNode node = getNext(minHeap);
            last.next = new ListNode(node.val);
            last = last.next;
        }
        return head;
    }

    private ListNode getNext(PriorityQueue<ListNode> minHeap) {
        ListNode node = minHeap.poll();
        if (node != null && node.next != null) {
            minHeap.offer(node.next);
        }
        return node;
    }

    public static void main(String[] args) {

    }
}
