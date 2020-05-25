package cn.axboy.demo.interview.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/lfu-cache/
// Least Frequently Used
public class LFUCache {

    private static class Node {
        int key;
        int val;
        int frequency;      //begin as 0
        long time = System.nanoTime();

        private void update() {
            frequency++;
            time = System.nanoTime();
        }
    }

    private final Map<Integer, Node> map;
    private final int capacity;
    private int size;
    private final PriorityQueue<Node> minHeap;

    public LFUCache(int capacity) {
        this.capacity = capacity;

        minHeap = new PriorityQueue<>((o1, o2) -> {
            int res = o1.frequency - o2.frequency;
            return res != 0 ? res : (int) (o1.time - o2.time);
        });
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        minHeap.remove(node);
        node.update();
        minHeap.offer(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (get(key) != -1) {
            // found, update
            Node node = map.get(key);
            node.val = value;
            return;
        }
        // insert new node
        if (capacity == size) {
            // remove oldest
            Node n = minHeap.poll();
            map.put(n.key, null);
            size--;
        }
        Node node = new Node();
        node.key = key;
        node.val = value;
        map.put(key, node);
        size++;
        minHeap.offer(node);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        //cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.put(5, 5);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);
    }
}
