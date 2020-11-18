import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        Class<Reflection> reflection = Reflection.class;


        Field[] fieldsArr = reflection.getDeclaredFields();
        Set<Field> fields = new TreeSet<>(Comparator.comparing(Field::getName));

        for (Field field : fieldsArr) {
            if (!Modifier.isPrivate(field.getModifiers())) {
                fields.add(field);
            }
        }

        for (Field field : fields) {
            System.out.printf("%s must be private!%n", field.getName());
        }


        Method[] declaredMethods = reflection.getDeclaredMethods();
        Set<Method> getters = new TreeSet<>(new MethodNameComparator());
        Set<Method> setters = new TreeSet<>(new MethodNameComparator());

        for (Method method : declaredMethods) {
            if (method.getName().startsWith("get") && !Modifier.isPublic(method.getModifiers())){
                getters.add(method);
            }else if (method.getName().startsWith("set") && !Modifier.isPrivate(method.getModifiers())){
                setters.add(method);
            }
        }

        for (Method getter : getters) {
            System.out.printf("%s have to be public!%n", getter.getName());
        }

        for (Method setter : setters) {
            System.out.printf("%s have to be private!%n", setter.getName());
        }
    }

    private static class MethodNameComparator implements Comparator<Method> {

        @Override
        public int compare(Method m1, Method m2) {
            return m1.getName().compareTo(m2.getName());
        }
    }
}
