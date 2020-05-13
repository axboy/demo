package cn.axboy.demo.interview.algorithm.dataStructure;

//线段树
public class SegmentTree<T> {

    private T[] data;

    private T[] tree;

    public SegmentTree(T[] arr) {
        data = (T[]) new Object[arr.length];
        for(int i = 0; i < arr.length;i++){
            data[i] = arr[i];
        }
        tree = (T[]) new Object[4 * arr.length];
    }

    private void buildSegmentTree(){

    }

    public int getSize() {
        return data.length;
    }

    public T get(int index) {
        return null;
    }

    private int lChild(int parent) {
        return 2 * parent + 1;
    }

    private int rChild(int parent) {
        return 2 * parent + 2;
    }
}
