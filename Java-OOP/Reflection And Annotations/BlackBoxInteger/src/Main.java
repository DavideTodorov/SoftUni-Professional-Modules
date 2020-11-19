import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            NoSuchFieldException {


        Scanner scanner = new Scanner(System.in);

        Class<BlackBoxInt> boxIntClass = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = boxIntClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();


        Method[] methods = boxIntClass.getDeclaredMethods();
        Map<String, Method> methodsMap = new HashMap<>();

        for (Method method : methods) {
            methodsMap.put(method.getName(), method);
        }


        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split("_");
            String methodName = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            Method method = methodsMap.get(methodName);
            method.setAccessible(true);
            method.invoke(blackBoxInt, value);

            Field innerValue = boxIntClass.getDeclaredField("innerValue");
            innerValue.setAccessible(true);
            int innerValueInt = innerValue.getInt(blackBoxInt);
            System.out.println(innerValueInt);


            input = scanner.nextLine();
        }
    }
}
