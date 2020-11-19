import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Class<RichSoilLand> richSoilLandClass = RichSoilLand.class;
        Field[] declaredFields = richSoilLandClass.getDeclaredFields();


        String input = scanner.nextLine();
        while (!"HARVEST".equals(input)) {

            switch (input) {
                case "private":
                    for (Field field : declaredFields) {
                        if (Modifier.isPrivate(field.getModifiers())) {
                            printField(field, "private");
                        }
                    }
                    break;

                case "protected":
                    for (Field field : declaredFields) {
                        if (Modifier.isProtected(field.getModifiers())) {
                            printField(field, "protected");
                        }
                    }
                    break;

                case "public":
                    for (Field field : declaredFields) {
                        if (Modifier.isPublic(field.getModifiers())) {
                            printField(field, "public");
                        }
                    }
                    break;

                case "all":
                    for (Field field : declaredFields) {
                        printField(field, Modifier.toString(field.getModifiers()));
                    }
                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static void printField(Field field, String modifier) {
        System.out.printf("%s %s %s%n",
                modifier,
                field.getType().getSimpleName(),
                field.getName());
    }
}
