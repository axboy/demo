package cn.axboy.demo.interview.common;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 15:44
 */
public class Node {

    private final Integer value;

    private Node next;

    public Node() {
        value = null;
        next = null;
    }

    public Node(Integer value) {
        this.value = value;
        next = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public Integer getValue() {
        return value;
    }

    public static void print(Node node) {
        if (node == null) {
            return;
        }
        Node curNode = node;
        while (curNode != null) {
            System.out.print(curNode.getValue() + " ");
            curNode = curNode.getNext();
        }
        System.out.println();
    }
}
