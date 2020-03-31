package cn.axboy.demo.interview.recursion;

import cn.axboy.demo.interview.common.Node;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 16:53
 * 创建链表
 */
public class LinkedListCreator {

    public static Node createLinkedList(List<Integer> data) {
        if (data == null || data.size() == 0) {
            return null;
        }

        Node headNode = new Node(data.get(0));
        Node subNodeList = createLinkedList(data.subList(1, data.size()));
        headNode.setNext(subNodeList);
        return headNode;
    }

    public static void main(String[] args) {
        Node.print(LinkedListCreator.createLinkedList(Collections.EMPTY_LIST));
        Node.print(LinkedListCreator.createLinkedList(Arrays.asList(1)));
        Node.print(LinkedListCreator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
    }
}
