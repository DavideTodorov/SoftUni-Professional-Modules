import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> products;

    public Instock() {
        products = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public boolean contains(Product product) {
        return products.contains(product);
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        Product productInStock = null;
        for (Product product1 : products) {
            if (product1.getLabel().equals(product)) {
                productInStock = product1;
                break;
            }
        }

        if (productInStock == null) {
            throw new IllegalArgumentException();
        }

        productInStock.setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        return products.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        Product productFound = null;
        for (Product product : products) {
            if (product.getLabel().equals(label)) {
                productFound = product;
                break;
            }
        }

        if (productFound == null) {
            throw new IllegalArgumentException();
        }

        return productFound;
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count <= 0 || count >= products.size()) {
            return new TreeSet<>();
        }

        return products.stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return products.stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return products.stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count <= 0 || count >= products.size()) {
            throw new IllegalArgumentException();
        }

        return products.stream()
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .limit(count)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return products.stream()
                .filter(p -> p.getQuantity() == quantity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
