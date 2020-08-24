package cn.axboy.demo.interview.classLoader;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/8 13:42
 * 封装加载类的信息
 */
public class LoadInfo {

    private ClassLoader classLoader;

    private long loadTime;

    private BaseManager baseManager;

    public LoadInfo(ClassLoader classLoader, long loadTime) {
        super();
        this.classLoader = classLoader;
        this.loadTime = loadTime;
    }

    public void setBaseManager(BaseManager baseManager) {
        this.baseManager = baseManager;
    }

    public BaseManager getBaseManager() {
        return baseManager;
    }

    public long getLoadTime() {
        return loadTime;
    }
}
