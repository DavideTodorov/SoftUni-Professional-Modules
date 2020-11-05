import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] peopleInputArr = scanner.nextLine().split(";");

        Map<String, Person> people = new LinkedHashMap<>();
        for (String curr : peopleInputArr) {
            String[] currPersonInfo = curr.split("=");

            String name = currPersonInfo[0];
            double money = Double.parseDouble(currPersonInfo[1]);

            try {
                Person person = new Person(name, money);
                people.put(name, person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String[] productInputArr = scanner.nextLine().split(";");

        Map<String, Product> products = new HashMap<>();
        for (String currProduct : productInputArr) {
            String[] currProductInfo = currProduct.split("=");

            String name = currProductInfo[0];
            double cost = Double.parseDouble(currProductInfo[1]);

            try {
                Product product = new Product(name, cost);
                products.put(name, product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] inputDetails = input.split("\\s+");

            Person person = people.get(inputDetails[0]);
            Product product = products.get(inputDetails[1]);

            try {
                person.buyProduct(product);
                System.out.println(String.format("%s bought %s",
                        person.getName(), product.getName()));

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            input = scanner.nextLine();
        }

        for (Person person : people.values()) {
            if (person.getProducts().isEmpty()) {
                System.out.println(String.format("%s – Nothing bought", person.getName()));

            } else {
                StringBuilder productsBought = new StringBuilder();

                List<Product> currPersonProducts = person.getProducts();

                for (int i = 0; i < currPersonProducts.size(); i++) {
                    if (i == currPersonProducts.size() - 1) {
                        productsBought
                                .append(currPersonProducts.get(i).toString());
                    } else {
                        productsBought
                                .append(currPersonProducts.get(i).toString())
                                .append(", ");
                    }
                }

                System.out.println(String.format("%s - %s",
                        person.getName(), productsBought.toString().trim()));
            }
        }
    }
}
