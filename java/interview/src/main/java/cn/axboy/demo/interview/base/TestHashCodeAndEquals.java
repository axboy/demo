package cn.axboy.demo.interview.base;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 20:40
 */
public class TestHashCodeAndEquals {

    private int num;

    public TestHashCodeAndEquals(int num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        return num;
    }

    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        return true;
    }

    public static void main(String[] args) {
        TestHashCodeAndEquals a = new TestHashCodeAndEquals(1);
        TestHashCodeAndEquals b = new TestHashCodeAndEquals(2);
        System.out.println("a == b:" + (a == b));
        System.out.println("a.equals(b):" + a.equals(b));
        System.out.format("a.hashCode = %d, b.hashCode = %d\n", a.hashCode(), b.hashCode());
    }
}
