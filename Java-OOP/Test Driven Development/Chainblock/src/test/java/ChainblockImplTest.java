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
}