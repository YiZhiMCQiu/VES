package cn.yizhimcqiu.ves.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public interface ListConverter<T, E> {
        T convert(E e);
    }
    public static <T, E> List<T> convertList(List<E> list, ListConverter<T, E> converter) {
        List<T> result = new ArrayList<>();
        for (E e : list) {
            result.add(converter.convert(e));
        }
        return result;
    }
}
