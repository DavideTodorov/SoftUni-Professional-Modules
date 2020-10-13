import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Use String as T
        CustomList<String> customList = new CustomList<>();

        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            switch (command) {
                case "Add":
                    String element = tokens[1];
                    customList.add(element);
                    break;

                case "Remove":
                    int index = Integer.parseInt(tokens[1]);
                    customList.remove(index);
                    break;

                case "Contains":
                    element = tokens[1];
                    System.out.println(customList.contains(element));
                    break;

                case "Swap":
                    int indexOne = Integer.parseInt(tokens[1]);
                    int indexTwo = Integer.parseInt(tokens[2]);
                    customList.swap(indexOne, indexTwo);
                    break;

                case "Greater":
                    element = tokens[1];
                    System.out.println(customList.countGreaterThan(element));
                    break;

                case "Max":
                    System.out.println(customList.getMax());
                    break;

                case "Min":
                    System.out.println(customList.getMin());
                    break;

                case "Print":
                    System.out.println(customList.toString());
                    break;

                case "Sort":
                    System.out.println(CustomList.sort(customList));
                    break;
            }

            input = scanner.nextLine();
        }
    }
}