package cn.axboy.demo.interview.collection;

import java.util.Objects;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/2 21:56
 */
public class DemoObject {

    private int id;

    private String name;

    public DemoObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoObject that = (DemoObject) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
