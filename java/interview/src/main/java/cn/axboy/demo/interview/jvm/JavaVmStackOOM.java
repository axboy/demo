package cn.axboy.demo.interview.jvm;

//Exceptioninthread"main"java.lang.OutOfMemoryError:unabletocreatenewnativethread
//Vm Args: -Xss2M
public class JavaVmStackOOM {
    private void noStop() {
        while (true) ;
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(() -> {
                noStop();
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVmStackOOM test = new JavaVmStackOOM();
        test.stackLeakByThread();
    }
}
