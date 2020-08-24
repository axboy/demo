package cn.axboy.demo.interview.java8;

import java.util.Arrays;
import java.util.List;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/19 13:58
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        IntHandler handler = (i) -> System.out.println(i);
        handler.handle(10);
    }
}
