import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            if (product1.getLabel().equals(product)){
                productInStock = product1;
            }
        }

        if (productInStock == null){
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
        return null;
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        return null;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return null;
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return null;
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        return null;
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return null;
    }

    @Override
    public Iterator<Product> iterator() {
        return null;
    }
}
