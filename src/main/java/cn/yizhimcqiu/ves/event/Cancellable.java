package cn.yizhimcqiu.ves.event;

public interface Cancellable {
    void cancel();
    boolean isCancelled();
}
