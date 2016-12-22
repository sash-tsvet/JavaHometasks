package Generic;
import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {

    private final HashMap<T, Integer> map = new HashMap<>();

    @Override
    public void add(T obj) {
        map.put(obj, map.getOrDefault(obj, 0) + 1);
    }

    @Override
    public int getCount(T obj) {
        return map.getOrDefault(obj, 0);
    }

    @Override
    public int remove(T obj) {
        int count = map.getOrDefault(obj, 0);
        map.remove(obj);
        return count;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        for (Map.Entry<? extends T, Integer> element : source.toMap().entrySet()) {
            map.put(element.getKey(), map.getOrDefault(element.getKey(), 0) + element.getValue());
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(map);
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        for (Map.Entry<T, Integer> element : map.entrySet()) {
            destination.put(element.getKey(), element.getValue());
        }
    }

    public static void main(String[] args) {

        CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        System.out.println(map.getCount(5)); // 2
        System.out.println(map.getCount(6)); // 1
        System.out.println(map.getCount(10)); // 3

        System.out.println(map.remove(10)); // 3

        System.out.println(map.size()); // 2

        System.out.println(map.toMap());

        map.addAll(map);

        System.out.println(map.toMap());

        Map<Integer, Integer> map1 = new HashMap<>();

        map.toMap(map1);

        System.out.println(map1);
    }
}
