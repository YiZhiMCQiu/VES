package cn.yizhimcqiu.ves.event;

import cn.yizhimcqiu.ves.event.listener.EventListener;

import java.util.ArrayList;
import java.util.List;

public class Event<T> {
    private static final List<Event<?>> EVENTS = new ArrayList<>();
    private final List<EventListener<T>> listeners = new ArrayList<>();
    public Event() {
        EVENTS.add(this);
    }
    public void on(EventListener<T> listener) {
        this.listeners.add(listener);
    }
    public boolean trigger(T event) {
        for (EventListener<T> listener : listeners) {
            listener.on(event);
        }
        return !(event instanceof Cancellable cancellable) || !cancellable.isCancelled();
    }
    public static void clear() {
        EVENTS.forEach(Event::clearListeners);
    }
    public void clearListeners() {
        this.listeners.clear();
    }
}
