import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductStockTest {
    private ProductStock products;

    @Before
    public void setUp() {
        products = new Instock();
    }

    @Test
    public void testAddingProducts() {
        Product product = new Product("test_label", 1.0, 1);
        products.add(product);
        boolean contains = products.contains(product);
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
        Product product = new Product("label_1", 3.00, 1);

        products.add(product);

        products.changeQuantity("not_existing_label", 10);
    }

    @Test
    public void testChangingQuantityToExistingProduct() {
        Product product = new Product("label_1", 3.00, 1);

        products.add(product);
        products.changeQuantity("label_1", 10);
        Product modifiedProduct = products.find(0);

        assertEquals(10, modifiedProduct.getQuantity());
    }
}