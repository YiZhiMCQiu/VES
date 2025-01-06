package cn.yizhimcqiu.ves.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListUtil {
    public static <T, E> List<T> convertList(List<E> list, Function<E, T> converter) {
        List<T> result = new ArrayList<>();
        for (E e : list) {
            result.add(converter.apply(e));
        }
        return result;
    }
    public static <T> T randomElement(List<T> list) {
        return list.get((int) Math.round(Math.random() * (list.size()-1)));
    }
    public static <T> T randomElement(List<T> list, Predicate<T> predicate) {
        T t = randomElement(list);
        while (!predicate.test(t)) {
            t = randomElement(list);
        }
        return t;
    }
}
