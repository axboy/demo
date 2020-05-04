package cn.axboy.demo.interview.algorithm.dataStructure;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/16 21:19
 * 二叉搜索树
 */
@Data
public class BinarySearchTree<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        final private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        //private int size;
        //private int depth;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private Node<K, V> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        root = insert(root, node);
    }

    private Node<K, V> insert(Node<K, V> parent, Node<K, V> node) {
        if (parent == null) {
            return node;
        }
        int compare = node.key.compareTo(parent.key);
        if (compare == 0) {
            parent.value = node.value;
        } else if (compare < 0) {
            parent.left = insert(parent.left, node);
        } else {
            parent.right = insert(parent.right, node);
        }
        return parent;
    }

    //查询key是否存在
    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node<K, V> parent, K key) {
        if (parent == null) {
            return false;
        }
        int compare = key.compareTo(parent.key);
        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return contains(parent.left, key);
        } else {
            return contains(parent.right, key);
        }
    }

    //搜索指定key，调用方保证key存在
    public Node<K, V> search(K key) {
        return search(root, key);
    }

    private Node<K, V> search(Node<K, V> parent, K key) {
        if (parent == null) {
            throw new RuntimeException("Please check key exist.");
        }
        int compare = key.compareTo(parent.key);
        if (compare == 0) {
            return parent;
        } else if (compare < 0) {
            return search(parent.left, key);
        } else {
            return search(parent.right, key);
        }
    }

    //前序遍历，先自身，递归左右子树
    public void preOrder() {
        System.out.println("bst pre order");
        preOrder(root);
    }

    private void preOrder(Node<K, V> parent) {
        if (parent == null) {
            return;
        }
        System.out.println(parent);
        preOrder(parent.left);
        preOrder(parent.right);
    }

    //中序遍历，先递归左子树，再访问自身，最后访问右子树
    public void inOrder() {
        System.out.println("bst in order");
        inOrder(root);
    }

    private void inOrder(Node<K, V> parent) {
        if (parent == null) {
            return;
        }
        inOrder(parent.left);
        System.out.println(parent);
        inOrder(parent.right);
    }

    //后序遍历，先递归访问左右子树，在访问自身
    public void postOrder() {
        System.out.println("bst post order");
        postOrder(root);
    }

    private void postOrder(Node<K, V> parent) {
        if (parent == null) {
            return;
        }
        postOrder(parent.left);
        postOrder(parent.right);
        System.out.println(parent);
    }

    //层序遍历，广度优先遍历
    public void levelOrder() {
        System.out.println("bst level order");
        Queue<Node<K, V>> queue = new LinkedList<>();
        if (root == null) {
            return;
        }
        queue.offer(root);
        while (queue.size() > 0) {
            Node<K, V> node = queue.poll();
            System.out.println(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    //以parent为根的最小值
    public Node<K, V> minimum(Node<K, V> parent) {
        if (parent == null) {
            return null;
        }
        Node<K, V> node = parent;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //以parent为根的最大值
    public Node<K, V> maximum(Node<K, V> parent) {
        if (parent == null) {
            return null;
        }
        Node<K, V> node = parent;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    //删除以parent为根的最大节点，返回当前的根
    private Node<K, V> removeMax(Node<K, V> parent) {
        if (parent == null) {
            return null;
        }
        if (parent.right == null) {
            return parent.left;
        }
        parent.right = removeMax(parent.right);
        return parent;
    }

    //删除key,返回对应的值
    public Node<K, V> remove(K key) {
        Node<K, V> node = search(key);
        root = remove(root, key);
        return node;
    }

    //删除以parent为根的key，返回此时的根节点
    private Node<K, V> remove(Node<K, V> parent, K key) {
        if (parent == null) {
            throw new RuntimeException("Can not find node");
        }
        int compare = key.compareTo(parent.key);

        if (compare < 0) {
            parent.left = remove(parent.left, key);
            return parent;
        } else if (compare > 0) {
            parent.right = remove(parent.right, key);
            return parent;
        }
        //compare == 0
        if (parent.left == null) {
            return parent.right;
        }
        if (parent.right == null) {
            return parent.left;
        }
        //从左子树中找最大值,作为新的根
        Node<K, V> lMaxNode = maximum(parent.left);
        lMaxNode.left = removeMax(parent.left);
        lMaxNode.right = parent.right;
        return lMaxNode;
    }

    //获取key的排名
    public int rank(K key) {
        return -1;
    }

    //第k大的值
    public Node<K, V> select(int k) {
        return null;
    }

    //比key小的最大节点
    public Node<K, V> floor(K key) {
        return null;
    }

    //比key大的最小节点
    public Node<K, V> ceil() {
        return null;
    }

    public static void main(String[] args) {
        //
        //       28
        //    /      \
        //   16      40
        //  /  \    /  \
        // 13  22  35  42
        //          \
        //           37
        BinarySearchTree<Integer, Integer> demo = new BinarySearchTree<>();
        demo.insert(28, 0);
        demo.insert(16, 0);
        demo.insert(40, 0);
        demo.insert(13, 0);
        demo.insert(22, 0);
        demo.insert(35, 0);
        demo.insert(42, 0);
        demo.insert(37, 0);
        //demo.preOrder();
        //demo.inOrder();
        //demo.postOrder();
        demo.levelOrder();
        //System.out.println("minimum: " + demo.minimum(demo.root));
        //System.out.println("maximum: " + demo.maximum(demo.root));

        //demo.removeMax(demo.root);
        //demo.remove(35);
        //demo.remove(40);
        //demo.remove(42);
        demo.levelOrder();
    }
}
