package cn.axboy.demo.interview.classLoader;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/8 14:05
 * 后台刷新热加载类
 */
public class MsgHandler implements Runnable {

    @Override
    public void run() {
        while (true) {
            BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
            manager.logic();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
