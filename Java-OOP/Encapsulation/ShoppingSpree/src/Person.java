import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {

    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    private void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void buyProduct(Product product) {
        checkIfPersonHasEnoughMoney(product);
        products.add(product);
        this.money -= product.getCost();
    }

    private void checkIfPersonHasEnoughMoney(Product product) {
        if (this.money < 0 || (this.money - product.getCost()) < 0) {
            throw new IllegalArgumentException(String.format("%s can't afford %s",
                    this.name, product.getName()));
        }
    }


}
