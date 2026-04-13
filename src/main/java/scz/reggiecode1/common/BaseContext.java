package scz.reggiecode1.common;

/**
 * 线程安全的存储登录用户ID
 * 使用ThreadLocal实现线程隔离
 */
public class BaseContext {

    /**
     * ThreadLocal是线程中的局部变量，可以为每个使用该变量的线程提供独立的变量副本，实现线程隔离和线程安全
     * 常用方法：
     * 1. public void set(T value)：设置当前线程的线程局部变量的值
     * 2. public T get()：获取当前线程对应的线程局部变量的值
     * 3. public void remove()：移除当前线程的线程局部变量
     */
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程的用户ID
     *
     * @param id 用户ID
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取当前线程的用户ID
     *
     * @return 用户ID
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    /**
     * 移除当前线程的用户ID
     */
    public static void remove() {
        threadLocal.remove();
    }
}
