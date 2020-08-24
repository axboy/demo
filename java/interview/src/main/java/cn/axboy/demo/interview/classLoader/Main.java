package cn.axboy.demo.interview.classLoader;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/8 14:11
 */
public class Main {
    public static void main(String[] args) {
        new Thread(new MsgHandler()).start();
    }
}
