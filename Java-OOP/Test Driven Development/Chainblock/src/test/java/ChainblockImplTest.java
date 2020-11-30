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


    @Test
    public void testGetBySenderOrderedByAmountDescending() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to_2", 2);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_2", "to_3", 3);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        Iterable<Transaction> sendersFrom_2 = chainblock.getBySenderOrderedByAmountDescending("from_2");
        List<Transaction> returnedTransactions = makeIteratorToList(sendersFrom_2);
        List<Transaction> expected = Arrays.asList(currTransaction2, currTransaction);

        assertEquals(expected, returnedTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByInvalidSenderOrderedByAmountDescending() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to_2", 2);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_3", "to_3", 2);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);

        Iterable<Transaction> sendersFrom_2 = chainblock.getBySenderOrderedByAmountDescending("from_4");
    }


    @Test
    public void testGetByReceiverOrderedByAmountDescendingThenById() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to_2", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_3", "to_2", 3);

        TransactionImpl currTransaction3 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_4", "to_2", 4);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);

        Iterable<Transaction> sendersFrom_2 = chainblock.getByReceiverOrderedByAmountThenById("to_2");
        List<Transaction> returnedTransactions = makeIteratorToList(sendersFrom_2);
        List<Transaction> expected = Arrays.asList(currTransaction3, currTransaction, currTransaction2);

        assertEquals(expected, returnedTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByInvalidReceiverOrderedByAmountDescendingThenById() {
        TransactionImpl currTransaction = new TransactionImpl(3, TransactionStatus.FAILED,
                "from_2", "to_2", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_3", "to_3", 3);

        TransactionImpl currTransaction3 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_4", "to_4", 4);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);

        chainblock.getByReceiverOrderedByAmountThenById("to_5");
    }


    @Test
    public void testGetTransactionByStatusAndMaximumAmount() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_2", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_3", "to_3", 3);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_4", "to_4", 4);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_5", "to_5", 5);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);

        Iterable<Transaction> transactionIterable =
                chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.ABORTED, 4);

        List<Transaction> returnedTransactions = makeIteratorToList(transactionIterable);
        List<Transaction> expected = Arrays.asList(currTransaction3, currTransaction2);

        assertEquals(expected, returnedTransactions);
    }

    @Test
    public void testGetTransactionByStatusAndInvalidMaximumAmount() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_2", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_3", "to_3", 3);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_4", "to_4", 4);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_5", "to_5", 5);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);

        Iterable<Transaction> transactionIterable =
                chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.ABORTED, 1);

        List<Transaction> returnedTransactions = makeIteratorToList(transactionIterable);
        List<Transaction> expected = Collections.emptyList();

        assertEquals(expected, returnedTransactions);
    }

    @Test
    public void testGetTransactionByInvalidStatusAndMaximumAmount() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_2", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_3", "to_3", 3);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_4", "to_4", 4);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_5", "to_5", 5);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);

        Iterable<Transaction> transactionIterable =
                chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.UNAUTHORIZED, 4);

        List<Transaction> returnedTransactions = makeIteratorToList(transactionIterable);
        List<Transaction> expected = Collections.emptyList();

        assertEquals(expected, returnedTransactions);
    }


    @Test
    public void testGetBySenderAndMinimumAmountDescending() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_2", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_2", "to_3", 4);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_2", "to_4", 5);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_2", "to_5", 6);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);

        Iterable<Transaction> from_2 = chainblock.getBySenderAndMinimumAmountDescending("from_2", 4);
        List<Transaction> returnedTransactions = makeIteratorToList(from_2);
        List<Transaction> expected = Arrays.asList(currTransaction4, currTransaction3);

        assertEquals(expected, returnedTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndInvalidMinimumAmountDescending() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_2", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_2", "to_3", 4);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_2", "to_4", 5);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_2", "to_5", 6);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);

        chainblock.getBySenderAndMinimumAmountDescending("from_2", 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByInvalidSenderAndMinimumAmountDescending() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_2", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_2", "to_3", 4);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_2", "to_4", 5);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_2", "to_5", 6);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);

        chainblock.getBySenderAndMinimumAmountDescending("from_3", 3);
    }


    @Test
    public void testGetByReceiverAndAmountInRangeSortedByAmountDescendingThenById() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_3", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_3", "to_3", 4);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_4", "to_3", 6);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_5", "to_3", 6);

        TransactionImpl currTransaction5 = new TransactionImpl(6, TransactionStatus.ABORTED,
                "from_6", "to_3", 7);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);
        chainblock.add(currTransaction5);

        Iterable<Transaction> to_3 = chainblock.getByReceiverAndAmountRange("to_3", 4, 7);
        List<Transaction> returnedTransactions = makeIteratorToList(to_3);
        List<Transaction> expected = Arrays.asList(currTransaction3, currTransaction4, currTransaction2);

        assertEquals(expected, returnedTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByInvalidReceiverAndAmountInRangeSortedByAmountDescendingThenById() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_3", 3);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_3", "to_3", 4);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_4", "to_3", 6);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_5", "to_7", 6);

        TransactionImpl currTransaction5 = new TransactionImpl(6, TransactionStatus.ABORTED,
                "from_6", "to_4", 7);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);
        chainblock.add(currTransaction5);

        chainblock.getByReceiverAndAmountRange("to_5", 4, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndInvalidAmountInRangeSortedByAmountDescendingThenById() {
        TransactionImpl currTransaction = new TransactionImpl(2, TransactionStatus.FAILED,
                "from_2", "to_3", 4);

        TransactionImpl currTransaction2 = new TransactionImpl(3, TransactionStatus.ABORTED,
                "from_3", "to_3", 5);

        TransactionImpl currTransaction3 = new TransactionImpl(4, TransactionStatus.ABORTED,
                "from_4", "to_3", 6);


        TransactionImpl currTransaction4 = new TransactionImpl(5, TransactionStatus.ABORTED,
                "from_5", "to_3", 6);

        TransactionImpl currTransaction5 = new TransactionImpl(6, TransactionStatus.ABORTED,
                "from_6", "to_3", 7);

        chainblock.add(testTransaction);
        chainblock.add(currTransaction);
        chainblock.add(currTransaction2);
        chainblock.add(currTransaction3);
        chainblock.add(currTransaction4);
        chainblock.add(currTransaction5);

        chainblock.getByReceiverAndAmountRange("to_3", 1, 4);
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