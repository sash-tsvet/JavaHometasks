package Reflection;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


class A {
	public Integer getVar() {
		return 3;
	}
}

class B {
	public void setVar(Number number) {
		System.out.println("setVar");
	}
}

public class BeanUtils {
	/**
	 * Scans object "from" for all getters. If object "to"
	 * contains correspondent setter, it will invoke it
	 * to set property value for "to" which equals to the property
	 * of "from".
	 * <p/>
	 * The type in setter should be compatible to the value returned
	 * by getter (if not, no invocation performed).
	 * Compatible means that parameter type in setter should
	 * be the same or be superclass of the return type of the getter.
	 * <p/>
	 * The method takes care only about public methods.
	 *
	 * @param to   Object which properties will be set.
	 * @param from Object which properties will be used to get values.
	 */

	public static void assign(Object to, Object from) {
		Map<String, Method> getters = new HashMap<>();
		Method[] methods = from.getClass().getMethods();

		System.out.println("Getters for from object " + from.getClass());
		for (Method getter : methods) {
			if (isGetter(getter)) {
				System.out.println("\tgetter: [" + getter.getName() + ", " + getter.getReturnType() + "]");
				getters.put(getter.getName().substring(3), getter);
			}
		}

		methods = to.getClass().getMethods();
		System.out.println("Setters from " + from.getClass());
		for (Method setter : methods) {
			if (isSetter(setter)) {
				System.out.println("\tsetter: [" + setter.getName() + ", " + setter.getParameterTypes()[0].getTypeName() + "]");
				Method getter = getters.getOrDefault(setter.getName().substring(3), null);

				if (getter != null && setter.getParameterTypes()[0].isAssignableFrom(getter.getReturnType())) {
					System.out.println("\t\tFount applicable getter: " + getter.getName());
					try {
						Object ret = getter.invoke(from);
						setter.invoke(to, ret);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static boolean isGetter(Method method) {
		return method.getName().startsWith("get") && method.getParameterTypes().length == 0 && !void.class.equals(method.getReturnType());
	}

	public static boolean isSetter(Method method) {
		return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
	}

	public static void main(String[] args) {
		assign(new B(), new A());
	}
}
