package cn.axboy.demo.interview.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/2 21:55
 */
public class CollectionTest {

    public static void main(String[] args) {
        DemoObject oa = new DemoObject(1, "a");
        DemoObject ob = new DemoObject(1, "b");

        Set<DemoObject> set = new HashSet<>();
        set.add(oa);
        set.forEach(it -> System.out.println(it.getName()));

        // add the same data
        set.add(ob);
        set.forEach(it -> System.out.println("name is " + it.getName()));    //name is a
    }
}
