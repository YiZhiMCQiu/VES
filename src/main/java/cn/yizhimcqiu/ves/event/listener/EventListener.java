package cn.yizhimcqiu.ves.event.listener;

import cn.yizhimcqiu.ves.event.Event;

public interface EventListener<T> {
    void on(T event);
}
