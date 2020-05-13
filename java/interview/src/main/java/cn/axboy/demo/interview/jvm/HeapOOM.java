package cn.axboy.demo.interview.jvm;

import java.util.ArrayList;
import java.util.List;

//test OutOfMemoryError: Java heap space
//VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
public class HeapOOM {
    public static class OOMObejct {
    }

    public static void main(String[] args) {
        List<OOMObejct> list = new ArrayList<>(2 << 15);
        while (true) {
            list.add(new OOMObejct());
        }
    }
}
