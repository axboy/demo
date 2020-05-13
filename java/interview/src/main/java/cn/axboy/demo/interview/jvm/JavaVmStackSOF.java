package cn.axboy.demo.interview.jvm;

//test StackOverflowError
//VM Args: -Xss128k
public class JavaVmStackSOF {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVmStackSOF test = new JavaVmStackSOF();
        test.stackLeak();
    }
}
