import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    @Test
    public void testFindingFirstNProductsInAlphabeticalOrderWithCorrectCount() {
        Product product1 = new Product("label_3", 3.00, 1);
        Product product2 = new Product("label_2", 3.00, 1);
        Product product3 = new Product("label_1", 3.00, 1);
        Product product4 = new Product("label_4", 3.00, 1);
        Product product5 = new Product("label_5", 3.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);

        Iterable<Product> foundProducts = products.findFirstByAlphabeticalOrder(3);
        List<Product> productsAsList = makeFoundProductsToList(foundProducts);
        List<Product> expected = Arrays.asList(product3, product2, product1);

        assertEquals(expected, productsAsList);
    }


    @Test
    public void testFindingAllProductsInPriceRange() {
        Product product1 = new Product("product1", 3.00, 1);
        Product product2 = new Product("product2", 4.00, 1);
        Product product3 = new Product("product3", 5.00, 1);
        Product product4 = new Product("product4", 6.00, 1);
        Product product5 = new Product("product5", 7.00, 1);
        Product product6 = new Product("product6", 8.00, 1);
        Product product7 = new Product("product7", 9.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        Iterable<Product> allInRange = products.findAllInRange(3, 7);
        List<Product> productsToList = makeFoundProductsToList(allInRange);
        List<Product> expected = Arrays.asList(product5, product4, product3, product2);

        assertEquals(expected, productsToList);
    }

    @Test
    public void testFindingAllProductsInPriceRangeWithProductsThatAreNotInThisRange() {
        Product product1 = new Product("product1", 1.00, 1);
        Product product2 = new Product("product2", 2.00, 1);
        Product product3 = new Product("product3", 3.00, 1);
        Product product4 = new Product("product4", 6.00, 1);
        Product product5 = new Product("product5", 7.00, 1);
        Product product6 = new Product("product6", 8.00, 1);
        Product product7 = new Product("product7", 9.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        Iterable<Product> allInRange = products.findAllInRange(3, 5);
        List<Product> productsToList = makeFoundProductsToList(allInRange);

        assertTrue(productsToList.isEmpty());
    }


    @Test
    public void testFindProductsByGivenPrice() {
        Product product1 = new Product("product1", 1.00, 1);
        Product product2 = new Product("product2", 2.00, 1);
        Product product3 = new Product("product3", 2.00, 1);
        Product product4 = new Product("product4", 3.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        Iterable<Product> allByPrice = products.findAllByPrice(2);
        List<Product> productsAsList = makeFoundProductsToList(allByPrice);
        List<Product> expected = Arrays.asList(product2, product3);

        assertEquals(expected, productsAsList);
    }

    @Test
    public void testFindProductsByGivenPriceThatNoneOfTheProductsHas() {
        Product product1 = new Product("product1", 1.00, 1);
        Product product2 = new Product("product2", 2.00, 1);
        Product product3 = new Product("product3", 2.00, 1);
        Product product4 = new Product("product4", 3.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        Iterable<Product> allByPrice = products.findAllByPrice(5);
        List<Product> productsAsList = makeFoundProductsToList(allByPrice);
        List<Product> expected = Arrays.asList(product2, product3);

        assertTrue(productsAsList.isEmpty());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstNMostExpensiveProductsWithCountLargerThanProductsCount() {
        Product product1 = new Product("product1", 5.00, 1);
        Product product2 = new Product("product2", 6.00, 1);
        Product product3 = new Product("product3", 7.00, 1);
        Product product4 = new Product("product4", 8.00, 1);
        Product product5 = new Product("product4", 9.00, 1);
        Product product6 = new Product("product4", 10.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);

        products.findFirstMostExpensiveProducts(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstNMostExpensiveProductsWithNegativeCount() {
        Product product1 = new Product("product1", 5.00, 1);
        Product product2 = new Product("product2", 6.00, 1);
        Product product3 = new Product("product3", 7.00, 1);
        Product product4 = new Product("product4", 8.00, 1);
        Product product5 = new Product("product4", 9.00, 1);
        Product product6 = new Product("product4", 10.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);

        products.findFirstMostExpensiveProducts(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstNMostExpensiveProductsWithZeroCount() {
        Product product1 = new Product("product1", 5.00, 1);
        Product product2 = new Product("product2", 6.00, 1);
        Product product3 = new Product("product3", 7.00, 1);
        Product product4 = new Product("product4", 8.00, 1);
        Product product5 = new Product("product4", 9.00, 1);
        Product product6 = new Product("product4", 10.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);

        products.findFirstMostExpensiveProducts(0);
    }

    @Test
    public void testFindFirstNMostExpensiveProductsWithValidCount() {
        Product product1 = new Product("product1", 5.00, 1);
        Product product2 = new Product("product2", 6.00, 1);
        Product product3 = new Product("product3", 7.00, 1);
        Product product4 = new Product("product4", 8.00, 1);
        Product product5 = new Product("product4", 9.00, 1);
        Product product6 = new Product("product4", 10.00, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);

        Iterable<Product> firstMostExpensiveProducts =
                products.findFirstMostExpensiveProducts(3);
        List<Product> productsReceived = makeFoundProductsToList(firstMostExpensiveProducts);
        List<Product> expected = Arrays.asList(product6, product5, product4);

        assertEquals(expected, productsReceived);
    }


    @Test
    public void testFindProductsByNotPresentQuantity() {
        Product product1 = new Product("product1", 5.00, 3);
        Product product2 = new Product("product2", 6.00, 4);
        Product product3 = new Product("product3", 7.00, 7);
        Product product4 = new Product("product4", 7.00, 7);
        Product product5 = new Product("product5", 7.00, 7);
        Product product6 = new Product("product6", 8.00, 8);
        Product product7 = new Product("product7", 9.00, 9);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        Iterable<Product> allByQuantity = products.findAllByQuantity(5);
        List<Product> productsFound = makeFoundProductsToList(allByQuantity);

        assertTrue(productsFound.isEmpty());
    }

    @Test
    public void testFindProductsByNegativeQuantity() {
        Product product1 = new Product("product1", 5.00, 3);
        Product product2 = new Product("product2", 6.00, 4);
        Product product3 = new Product("product3", 7.00, 7);
        Product product4 = new Product("product4", 7.00, 7);
        Product product5 = new Product("product5", 7.00, 7);
        Product product6 = new Product("product6", 8.00, 8);
        Product product7 = new Product("product7", 9.00, 9);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        Iterable<Product> allByQuantity = products.findAllByQuantity(-1);
        List<Product> productsFound = makeFoundProductsToList(allByQuantity);

        assertTrue(productsFound.isEmpty());
    }

    @Test
    public void testFindProductsByValidQuantity() {
        Product product1 = new Product("product1", 5.00, 3);
        Product product2 = new Product("product2", 6.00, 4);
        Product product3 = new Product("product3", 7.00, 7);
        Product product4 = new Product("product4", 7.00, 7);
        Product product5 = new Product("product5", 7.00, 7);
        Product product6 = new Product("product6", 8.00, 8);
        Product product7 = new Product("product7", 9.00, 9);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        Iterable<Product> allByQuantity = products.findAllByQuantity(7);
        List<Product> productsFound = makeFoundProductsToList(allByQuantity);
        List<Product> expected = Arrays.asList(product3, product4, product5);

        assertEquals(expected, productsFound);
    }


    @Test
    public void testGetAllProductsInStock() {
        Product product1 = new Product("product1", 5.00, 3);
        Product product2 = new Product("product2", 6.00, 4);
        Product product3 = new Product("product3", 7.00, 7);
        Product product4 = new Product("product4", 7.00, 7);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        Iterator<Product> productIterator = products.iterator();
        List<Product> productsReceived = new ArrayList<>();

        while (productIterator.hasNext()) {
            Product next = productIterator.next();
            productsReceived.add(next);
            productIterator.remove();
        }

        List<Product> expectedProducts = Arrays.asList(product1, product2, product3, product4);

        assertEquals(expectedProducts, productsReceived);
    }

    private <T> List<T> makeFoundProductsToList(Iterable<T> foundProducts) {
        List<T> result = new ArrayList<>();

        for (T product : foundProducts) {
            result.add(product);
        }

        return result;
    }
}