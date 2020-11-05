import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaInput = scanner.nextLine().split("\\s+");
        String[] doughInput = scanner.nextLine().split("\\s+");

        Pizza pizza = null;
        try {
            //Create Pizza
            pizza = new Pizza(pizzaInput[1], Integer.parseInt(pizzaInput[2]));

            //Create Dough
            Dough dough = new Dough(doughInput[1], doughInput[2],
                    Double.parseDouble(doughInput[3]));

            //Add dough
            pizza.setDough(dough);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }


        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split("\\s+");

            try {
                Topping topping = new Topping(tokens[1], Double.parseDouble(tokens[2]));
                pizza.addTopping(topping);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }

            input = scanner.nextLine();
        }
        System.out.println();

        System.out.println(String.format("%s - %.2f",
                pizza.getName(), pizza.getOverallCalories()));
    }
}
