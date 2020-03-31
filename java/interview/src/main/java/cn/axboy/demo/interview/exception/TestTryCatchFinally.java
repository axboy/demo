package cn.axboy.demo.interview.exception;

import java.io.IOException;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 22:45
 */
public class TestTryCatchFinally {

    private void throwUnCheckedException() {
        throw new RuntimeException("UnCheckedException");
    }

    private void throwCheckedException() throws IOException {
        throw new IOException("CheckedException");
    }


    //return 1
    public int fun1() {
        try {
            System.out.println("try return 0");
            return 0;
        } finally {
            System.out.println("finally return 1");
            return 1;
        }
    }

    //无任何显示，正常返回1
    public int fun2() {
        try {
            throwUnCheckedException();
            throwCheckedException();
        } finally {
            return 1;
        }
    }

    public static void main(String[] args) {
        TestTryCatchFinally test = new TestTryCatchFinally();
        System.out.println("fun1: " + test.fun1());
        System.out.println("fun2: " + test.fun2());
    }
}
