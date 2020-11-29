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
                "from_1", "to_1", 3.0);
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
        List<Transaction> transactionsReceived = makeIteratorToList(iterator);

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


    @Test(expected = IllegalArgumentException.class)
    public void testGettingByInvalidId() {
        chainblock.getById(2);
    }

    @Test
    public void testGettingByValidId() {
        chainblock.add(testTransaction);
        Transaction byId = chainblock.getById(1);
        assertEquals(testTransaction, byId);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemovingTransactionByInvalidId() {
        chainblock.removeTransactionById(2);
    }

    @Test
    public void testRemovingTransactionByValidId() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from", "to", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);

        chainblock.removeTransactionById(1);

        Iterator<Transaction> iterator = chainblock.iterator();
        List<Transaction> returnedList = makeIteratorToList(iterator);
        List<Transaction> expected = Collections.singletonList(currTransaction);

        assertEquals(expected, returnedList);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGettingTransactionsByInvalidStatus() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from", "to", 2);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.FAILED,
                "from", "to", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
    }

    @Test
    public void testGettingTransactionsByValidStatus() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from", "to", 2);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from", "to", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        Iterable<Transaction> byTransactionStatus = chainblock.getByTransactionStatus(TransactionStatus.ABORTED);
        List<Transaction> returnedTransactions = makeIteratorToList(byTransactionStatus);
        List<Transaction> expected = Arrays.asList(currTransaction, currTransaction2);

        assertEquals(expected, returnedTransactions);
    }


    @Test
    public void testGettingSendersWithValidStatus() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to", 1);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_3", "to", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        Iterable<String> senders = chainblock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
        List<String> returnedSenders = makeIteratorToList(senders);
        List<String> expected = Arrays.asList("from_1", "from_2");


        assertEquals(expected, returnedSenders);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGettingSendersWithInvalidStatus() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to", 1);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_3", "to", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
    }


    @Test
    public void testGettingReceiversWithValidStatus() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to_2", 1);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_3", "to_3", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        Iterable<String> senders = chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);
        List<String> returnedSenders = makeIteratorToList(senders);
        List<String> expected = Arrays.asList("to_1", "to_2");


        assertEquals(expected, returnedSenders);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGettingReceiversWithInvalidStatus() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to_2", 1);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_3", "to_3", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
    }


    @Test
    public void testGettingAllTransactionsOrderedByAmountDescendingThenById() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to_2", 2);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_3", "to_3", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        Iterable<Transaction> received = chainblock.getAllOrderedByAmountDescendingThenById();
        List<Transaction> returnedTransactions = makeIteratorToList(received);
        List<Transaction> expected = Arrays.asList(testTransaction, currTransaction, currTransaction2);

        assertEquals(expected, returnedTransactions);
    }


    private <T> List<T> makeIteratorToList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            list.add(iterator.next());
            iterator.remove();
        }

        return list;
    }

    private <T> List<T> makeIteratorToList(Iterable<T> iterator) {
        List<T> list = new ArrayList<>();

        for (T element : iterator) {
            list.add(element);
        }

        return list;
    }
}