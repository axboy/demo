package cn.axboy.demo.interview.collection;

import cn.axboy.demo.interview.common.Node;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 16:02
 */
public class TestMyLinkedList {

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println("循环遍历");
        Node curNode = list.getHead();
        while (curNode != null) {
            System.out.print(curNode.getValue() + " ");
            curNode = curNode.getNext();
        }
        System.out.println("\nforeach");
        for (Integer v : list) {
            System.out.print(v + " ");
        }
    }
}
