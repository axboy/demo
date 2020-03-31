package cn.axboy.demo.interview.collection;

import cn.axboy.demo.interview.common.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 16:00
 * @description TODO
 */
public class MyLinkedList implements Iterable<Integer> {

    private Node head;

    private Node tail;

    private int size;

    public Node getHead() {
        return head;
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(Integer v) {
        Node node = new Node(v);
        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new LikedListIterator(head);
    }

    private static class LikedListIterator implements Iterator<Integer> {

        Node curNode;

        public LikedListIterator(Node curNode) {
            this.curNode = curNode;
        }

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public Integer next() {
            if (curNode == null) {
                throw new NoSuchElementException();
            }
            Integer value = curNode.getValue();
            curNode = curNode.getNext();
            return value;
        }
    }
}
