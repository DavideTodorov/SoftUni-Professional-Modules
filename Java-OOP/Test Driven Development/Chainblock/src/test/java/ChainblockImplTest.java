import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    private Chainblock chainblock;
    private Transaction testTransaction;

    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();
        this.testTransaction = new TransactionImpl(1, TransactionStatus.FAILED,
                "From_test", "To_test", 5.0);
    }


    @Test
    public void testChainblockContainsNotPresentTransaction() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from", "to", 2);

        chainblock.add(testTransaction);
        boolean contains = chainblock.contains(currTransaction);

        assertFalse(contains);

        chainblock.add(currTransaction);
        boolean containsAfterAdding = chainblock.contains(testTransaction);

        assertTrue(containsAfterAdding);
    }


    @Test
    public void testAddingNotExistingTransaction() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from", "to", 2);
        chainblock.add(testTransaction);
        chainblock.add(currTransaction);

        Iterator<Transaction> iterator = chainblock.iterator();
        List<Transaction> transactionsReceived = new ArrayList<>();

        while (iterator.hasNext()) {
            Transaction next = iterator.next();
            transactionsReceived.add(next);
            iterator.remove();
        }

        List<Transaction> expected = Arrays.asList(testTransaction, currTransaction);

        assertEquals(expected, transactionsReceived);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingAlreadyExistingTransaction() {
        chainblock.add(testTransaction);
        chainblock.add(testTransaction);
    }


    @Test
    public void testChainBlockContainsIdThatIsNotPresent() {
        chainblock.add(testTransaction);

        boolean contains = chainblock.contains(2);
        assertFalse(contains);

        contains = chainblock.contains(1);
        assertTrue(contains);
    }


    @Test
    public void testChainblockGetcount() {
        chainblock.add(testTransaction);

        int count = chainblock.getCount();

        assertEquals(1, count);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testChangingStatusToNotPresentTransaction() {
        chainblock.add(testTransaction);

        chainblock.changeTransactionStatus(2, TransactionStatus.ABORTED);
    }

    @Test
    public void testChangingStatusToPresentTransaction() {
        chainblock.add(testTransaction);

        chainblock.changeTransactionStatus(1, TransactionStatus.ABORTED);
        Transaction foundById = chainblock.getById(1);


        assertEquals(TransactionStatus.ABORTED, foundById.getStatus());
    }

}