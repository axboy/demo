package cn.axboy.demo.interview.base;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 20:19
 */
class Demo {
    private int id;

    public Demo(int id) {
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("cur id: " + id);
    }
}

public class TestFinalize {
    public static void fun() {
        new Demo(1);
        Demo demo = new Demo(2);
    }

    public static void main(String[] args) {
        int i = 0;
        while (i++ < 10) {
            fun();
            System.gc();
        }
    }
}
