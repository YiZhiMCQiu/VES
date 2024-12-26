package cn.yizhimcqiu.ves.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListUtil {
    public static <T, E> List<T> convertList(List<E> list, Function<E, T> converter) {
        List<T> result = new ArrayList<>();
        for (E e : list) {
            result.add(converter.apply(e));
        }
        return result;
    }
}
