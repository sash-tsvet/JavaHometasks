package Java8;import java.util.*;

public class Streams<T> {

	interface Filter<T> {
		boolean filter(T obj);
	}

	interface Transform<T, R> {
		R transform(T obj);
	}

	interface MapKey<K, T> {
		K getKey(T obj);
	}

	interface MapVal<V, T> {
		V getVal(T obj);
	}

	private List<T> list;

	public static <E> Streams<E> of(List<E> list) {
		return new Streams<>(list);
	}

	public Streams(List<T> list) {
		this.list = new ArrayList<>(list);
	}

	public Streams<T> filter(Filter<? super T> f) {
		list.removeIf(element -> !f.filter(element));
		return this;
	}

	public <R> Streams<R> transform(Transform<? super T, R> t) {
		List<R> tmp = new ArrayList<>();
		for (T element : list) {
			tmp.add(t.transform(element));
		}
		return new Streams<>(tmp);
	}

	public <K, V> Map<K, V> toMap(MapKey<K, T> k, MapVal<V, T> v) {
		Map<K, V> map = new HashMap<>();
		for (T element : list) {
			map.put(k.getKey(element), v.getVal(element));
		}
		return map;
	}

	public static void main(String[] args) {
		List<Person.Persona> list = new ArrayList<>();

		list.add(new Person.Persona(22, "Sasha"));
		list.add(new Person.Persona(23, "Fuck him"));
		list.add(new Person.Persona(24, "Java"));

		System.out.println(
				Streams.of(list).filter(p -> p.getAge() > 22).transform(p -> p.getAge() + 20).toMap(p -> p + 10, p -> p - 10)
		);
		System.out.println(
				Streams.of(list).filter(p -> p.getAge() < 25).transform(p -> new Person.Persona(p.getAge() + 20, p.getName() + "1")).toMap(p -> p.getName(), p -> p.getAge())
		);
	}
}
