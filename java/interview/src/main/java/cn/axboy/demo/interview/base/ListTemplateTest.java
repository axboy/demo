package cn.axboy.demo.interview.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/1 15:24
 */
public class ListTemplateTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        //List<Object> objectList = list;
    }
}
