import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductStockTest {
    private ProductStock products;
    private Product testProduct;

    @Before
    public void setUp() {
        products = new Instock();
        testProduct = new Product("test_label", 1.0, 1);
    }

    @Test
    public void testAddingProducts() {
        products.add(testProduct);
        boolean contains = products.contains(testProduct);
        assertTrue(contains);
    }


    @Test
    public void testReturnsCorrectCountOfProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        products.add(product1);
        products.add(product2);
        products.add(product3);

        int count = products.getCount();

        assertEquals(3, count);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindingByInvalidIndex() {
        products.add(new Product());
        products.find(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindingByNegativeIndex() {
        products.find(-1);
    }

    @Test
    public void testFindingByValidIndex() {
        Product product1 = new Product("label_1", 3.00, 1);
        Product product2 = new Product("label_2", 3.00, 1);
        Product product3 = new Product("label_3", 3.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);

        Product productFound = products.find(1);
        assertEquals(product2, productFound);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testChangingQuantityToNoneExistingProduct() {
        products.add(testProduct);

        products.changeQuantity("not_existing_label", 10);
    }

    @Test
    public void testChangingQuantityToExistingProduct() {
        products.add(testProduct);
        products.changeQuantity("test_label", 10);
        Product modifiedProduct = products.find(0);

        assertEquals(10, modifiedProduct.getQuantity());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testFindProductByNoneExistingLabel() {
        products.add(testProduct);
        products.findByLabel("not_existing_label");
    }

    @Test
    public void testFindProductByExistingLabel() {
        products.add(testProduct);
        products.add(new Product("test1", 1, 1));

        Product productFound = products.findByLabel("test_label");
        assertEquals(testProduct, productFound);
    }


    @Test
    public void testFindingFirstNProductsInAlphabeticalOrderWithInvalidCount() {
        Product product1 = new Product("label_1", 3.00, 1);
        Product product2 = new Product("label_2", 3.00, 1);
        Product product3 = new Product("label_3", 3.00, 1);
        Product product4 = new Product("label_4", 3.00, 1);
        Product product5 = new Product("label_5", 3.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);

        Iterable<Product> foundProducts = products.findFirstByAlphabeticalOrder(6);
        List<Product> productsAsList = makeFoundProductsToList(foundProducts);

        assertTrue(productsAsList.isEmpty());
    }

    @Test
    public void testFindingFirstNProductsInAlphabeticalOrderWithNegativeCount() {
        Product product1 = new Product("label_1", 3.00, 1);
        Product product2 = new Product("label_2", 3.00, 1);
        Product product3 = new Product("label_3", 3.00, 1);
        Product product4 = new Product("label_4", 3.00, 1);
        Product product5 = new Product("label_5", 3.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);

        Iterable<Product> foundProducts = products.findFirstByAlphabeticalOrder(-1);
        List<Product> productsAsList = makeFoundProductsToList(foundProducts);

        assertTrue(productsAsList.isEmpty());
    }

    @Test
    public void testFindingFirstNProductsInAlphabeticalOrderWithZeroCount() {
        Product product1 = new Product("label_1", 3.00, 1);
        Product product2 = new Product("label_2", 3.00, 1);
        Product product3 = new Product("label_3", 3.00, 1);
        Product product4 = new Product("label_4", 3.00, 1);
        Product product5 = new Product("label_5", 3.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);

        Iterable<Product> foundProducts = products.findFirstByAlphabeticalOrder(0);
        List<Product> productsAsList = makeFoundProductsToList(foundProducts);

        assertTrue(productsAsList.isEmpty());
    }






    private <T> List<T> makeFoundProductsToList(Iterable<T> foundProducts) {
        List<T> result = new ArrayList<>();

        for (T product : foundProducts) {
            result.add(product);
        }

        return result;
    }
}