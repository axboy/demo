package cn.axboy.demo.interview.algorithm.dataStructure;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/21 13:17
 * 二叉最大堆，父节点大于子节点，是一个完全二叉树
 * 索引从0开始，假设数据都大于0，不做数据判断
 */
public class BinaryMaxHeap {

    private int[] data;
    private int size;

    //创建一个空堆
    public BinaryMaxHeap(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    //传入数组，构建最大堆
    public BinaryMaxHeap(int[] arr) {
        data = arr;
        size = arr.length;
        heapify();
    }

    //原地构建堆
    private void heapify() {

    }

    //插入一个元素
    public void offer(int element) {
        if (size == data.length) {
            grow();
        }
        data[size] = element;
        shiftUp(size);
        //shiftUpLoop(size);
        size++;
        //print();
    }

    //取出最大值，取出堆顶，把最后一个元素放入堆顶，shiftDown
    public int poll() {
        if (size == 0) {
            throw new RuntimeException("Empty Heap");
        }
        size--;
        int value = data[0];
        data[0] = data[size];
        data[size] = 0;
        //shiftDown(0);
        shiftDownLoop(0);
        return value;
    }

    //获取当前堆的大小
    public int getSize() {
        return size;
    }

    //自动扩展数组
    private void grow() {
        throw new RuntimeException("UnSupportedMethod");
    }

    //将index处的值尝试上移，使数据维持堆的性质x
    private void shiftUp(int index) {
        if (index == 0) {
            return;
        }
        int p = parent(index);
        if (data[p] < data[index]) {
            swap(p, index);
            shiftUp(p);
        }
    }

    //循环实现shiftUp
    private void shiftUpLoop(int index) {
        int p = parent(index);
        while (p > 0 && data[p] < data[index]) {
            swap(p, index);
            p = parent(p);
        }
    }

    private void shiftDown(int index) {
        int l = left(index);
        int r = right(index);
        // 没有左孩子
        if (l > getSize() - 1) {
            return;
        }
        // 没有右孩子
        if (r > getSize() - 1) {
            if (data[l] > data[index]) {
                swap(l, index);
            }
            return;
        }
        //有左右子树，继续递归
        int max = data[l] < data[r] ? r : l;
        if (data[max] > data[index]) {
            swap(max, index);
            shiftDown(max);
        }
    }

    //循环实现shiftDown
    private void shiftDownLoop(int index) {
        int l = left(index);
        int r = right(index);
        while (l <= getSize() - 1) {
            int max = l;
            if (r <= getSize() - 1 && data[l] < data[r]) {
                max = r;
            }
            if (data[max] < data[index]) {
                break;
            }
            swap(max, index);
            index = max;
            l = left(index);
            r = right(index);
        }
    }

    //交换元素
    private void swap(int a, int b) {
        int tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }

    //根据子节点下标，获取父节点下标
    //从1开始: p = c / 2
    //从0开始: p = (c + 1) / 2 - 1
    private int parent(int childIndex) {
        return (childIndex + 1) / 2 - 1;
    }

    //根据父节点下标，获取左子节点下标，不处理下标越界
    //从1开始: l = p * 2
    //从0开始: l = (p + 1) * 2 - 1 = 2 * p + 1
    private int left(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    //根据父节点下标，获取右子节点下标，不处理下标越界
    //从1开始: r = p * 2 + 1
    //从0开始: r = (p + 1) * 2 = 2 * p + 2
    private int right(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    public void print() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BinaryMaxHeap demo = new BinaryMaxHeap(10);
        demo.offer(7);
        demo.offer(4);
        demo.offer(6);
        demo.offer(5);
        demo.offer(1);
        demo.offer(2);
        demo.offer(3);
        while (demo.getSize() > 0) {
            System.out.println(">> " + demo.poll());
            //demo.print();
        }
    }
}
