package cn.axboy.demo.interview.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// https://leetcode-cn.com/problems/lru-cache/
// Least Frequently Used
// 双map实现lfu
public class LFUCacheWithTwoMap {

    private static class Node {
        int key;
        int val;
        int frequency;      // begin as 0
    }

    private final Map<Integer, Node> cacheMap;
    private final Map<Integer, LinkedList<Node>> frequencyMap;
    private final int capacity;
    private int size;
    private int minFrequency;

    public LFUCacheWithTwoMap(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>();
        frequencyMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cacheMap.get(key);
        if (node == null) {
            return -1;
        }
        // if exist, oldList can not be null, then remove node
        LinkedList<Node> oldList = frequencyMap.get(node.frequency);
        oldList.remove(node);

        // if old min map is empty, minFrequency
        if (minFrequency == node.frequency && oldList.size() == 0) {
            minFrequency++;
        }

        node.frequency += 1;
        LinkedList<Node> newList = frequencyMap.computeIfAbsent(node.frequency, k -> new LinkedList<>());
        newList.addFirst(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (get(key) != -1) {
            // found, update
            Node node = cacheMap.get(key);
            node.val = value;
            return;
        }
        // if cache full, remove last
        if (size == capacity) {
            LinkedList<Node> minList = frequencyMap.get(minFrequency);
            Node minNode = minList.getLast();
            minList.removeLast();
            cacheMap.remove(minNode.key);
            size--;
        }
        // insert new node
        Node node = new Node();
        node.key = key;
        node.val = value;
        minFrequency = node.frequency;

        LinkedList<Node> curList = frequencyMap.computeIfAbsent(minFrequency, k -> new LinkedList<>());
        curList.addFirst(node);

        cacheMap.put(key, node);
        size++;
    }

    public static void main(String[] args) {
        LFUCacheWithTwoMap cache = new LFUCacheWithTwoMap(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.get(4);
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
