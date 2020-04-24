package cn.axboy.demo.interview.algorithm.dataStructure;

import lombok.Data;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/16 21:19
 * 二叉搜索树
 */
@Data
public class BinarySearchTree<K, V> {

    private static class Node<K, V> {
        private K key;
        private V value;
        private BinarySearchTree<K, V> left;
        private BinarySearchTree<K, V> right;
        //private int size;
    }

    private Node<K, V> root;
    private int size;


    public void insert() {
    }

    public boolean contain() {
        return false;
    }

    public void search() {
    }

    //前序遍历，先自身，递归左右子树
    public void preOrder() {
    }

    //中序遍历，先递归左子树，再访问自身，最后访问右子树
    public void inOrder() {
    }

    //后序遍历，先递归访问左右子树，在访问自身
    public void postOrder() {
    }

    //层序遍历，广度优先遍历
    public void levelOrder() {
    }

    public Node<K, V> minium() {
        return null;
    }

    public Node<K, V> maxium() {
        return null;
    }

    public void remove() {
    }

    public void rank() {
    }

    public void select() {
    }

    public void floor() {

    }

    public void ceil() {

    }
}
