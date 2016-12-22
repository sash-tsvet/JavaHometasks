package Generic;

import java.util.*;

public class CollectionUtils {


    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? super T> source, T obj) {
        return source.indexOf(obj);
    }


    public static<T> List<T> limit(List<T> source, int size) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(source.get(i));
        }
        return list;
    }

    public static <T> void add(List<? super T> source, T obj) {
        source.add(obj);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<T> c1, List<? extends T> c2) { //return c1.containsAll(c2); }
        Set<T> set = new HashSet<>(c1);
        for (T element : c2) {
            if (!set.contains(element)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean containsAny(List<T> c1, List<? extends T> c2) {
        Set<T> set = new HashSet<>(c1);
        for (T element : c2) {
            if (set.contains(element)) {
                return true;
            }
        }
        return false;
    }

    public static <E, T extends Comparable<? super E>> List range(List<T> list, E min, E max) {
        List<T> tmp = CollectionUtils.newArrayList();
        for (T element : list)
            if (element.compareTo(min) >= 0 && element.compareTo(max) <= 0) {
                tmp.add(element);
            }
        return tmp;
    }

    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> tmp = CollectionUtils.newArrayList();
        for (T element : list) {
            if (comparator.compare(element, min) >= 0 && comparator.compare(element, max) <= 0) {
                tmp.add(element);
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        List<Integer> listInt = CollectionUtils.newArrayList();
        List<Double> listDouble = CollectionUtils.newArrayList();
        List<Number> listNum = CollectionUtils.newArrayList();

        CollectionUtils.add(listInt, 1);
        CollectionUtils.add(listInt, 2);
        CollectionUtils.add(listInt, 3);
        System.out.println(listInt);

        CollectionUtils.add(listDouble, 4.0);
        CollectionUtils.add(listDouble, 5.0);
        CollectionUtils.add(listDouble, 6.0);
        System.out.println(listDouble);

        CollectionUtils.add(listNum, -2);
        CollectionUtils.add(listNum, -1.0);
        System.out.println(listNum);

        CollectionUtils.addAll(listInt, listNum);
        CollectionUtils.addAll(listDouble, listNum);
        System.out.println(listNum);

        System.out.println(CollectionUtils.limit(listInt, 2));

        System.out.println(CollectionUtils.containsAll(listNum, listInt));
        System.out.println(CollectionUtils.containsAll(listNum, listDouble));
        System.out.println(CollectionUtils.containsAll(CollectionUtils.limit(listNum, 7), listDouble));
        System.out.println(CollectionUtils.containsAll(CollectionUtils.limit(listNum, 6), listInt));

        System.out.println(CollectionUtils.indexOf(listNum, -1));
        System.out.println(CollectionUtils.indexOf(listNum, -1.0));
        System.out.println(CollectionUtils.indexOf(listNum, 3));
        System.out.println(CollectionUtils.indexOf(listNum, 3.0));
        System.out.println(CollectionUtils.indexOf(listNum, 4.0));
        System.out.println(CollectionUtils.indexOf(listNum, 4));

        CollectionUtils.removeAll(listNum, listInt);
        System.out.println(listNum);

        System.out.println(CollectionUtils.containsAny(listNum, listDouble));
        System.out.println(CollectionUtils.containsAny(listNum, listInt));

        System.out.println(CollectionUtils.range(listInt, 2, 2));
        System.out.println(CollectionUtils.range(listDouble, 4.5, 5.5));
        System.out.println(CollectionUtils.range(listNum, 0, 5.5, Comparator.comparing(Number::doubleValue)));
    }

}
