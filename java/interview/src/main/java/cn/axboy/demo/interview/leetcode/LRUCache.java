package cn.axboy.demo.interview.leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/lru-cache/
// Least Recently Used
public class LRUCache {

    private static class Node {
        Node next;
        Node pre;
        int key;
        int val;
    }

    private final Map<Integer, Node> map;
    private final int capacity;
    private int size;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(size);
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        if (node.pre == null) {
            return node.val;
        }
        //backup
        Node pre = node.pre;
        Node next = node.next;

        //mv head
        node.next = head;
        head.pre = node;
        head = node;
        head.pre = null;

        //reconnect
        pre.next = next;
        if (next != null) {
            next.pre = pre;
        } else {
            tail = pre;
        }
        return head.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        int val = get(key);
        if (val != -1) {
            Node node = map.get(key);
            node.val = value;
            return;
        }
        Node node = new Node();
        node.val = value;
        node.key = key;
        if (size == capacity) {
            // del oldest node
            Node pre = tail.pre;
            map.put(tail.key, null);   //map.delete(tail.key);
            if (pre != null) {
                pre.next = null;
            }
            tail = pre;
            size--;
        }

        //add map
        map.put(key, node);
        size++;
        //first node
        if (head == null || tail == null) {
            head = tail = node;
            return;
        }
        // add new node
        head.pre = node;
        node.next = head;
        head = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);                        // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);                        // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}
