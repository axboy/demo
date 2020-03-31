package cn.axboy.demo.interview.recursion;

import cn.axboy.demo.interview.common.Node;

import java.util.Arrays;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 17:13
 */
public class LinkedListReverse {

    //递归逆转链表
    public static Node reverseLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            return head;
        }
        Node newHead = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    //循环逆转链表
    public static Node reverseLinkedListByLoop(Node head) {
        if (head == null) {
            return null;
        }
        Node lastNode = null;
        Node curHead = head;
        Node next;
        do {
            next = curHead.getNext();
            curHead.setNext(lastNode);
            if (next == null) {
                return curHead;
            }
            lastNode = curHead;
            curHead = next;
        } while (true);
    }

    public static void main(String[] args) {
        Node head = LinkedListCreator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Node.print(head);
        Node newHead = LinkedListReverse.reverseLinkedList(head);
        Node.print(newHead);

        head = LinkedListCreator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        newHead = LinkedListReverse.reverseLinkedListByLoop(head);
        Node.print(newHead);
    }
}
