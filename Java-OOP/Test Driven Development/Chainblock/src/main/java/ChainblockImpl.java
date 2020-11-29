import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChainblockImpl implements Chainblock {
    private List<Transaction> transactionList;

    public ChainblockImpl() {
        this.transactionList = transactionList;
        transactionList = new ArrayList<>();

    }


    public int getCount() {
        return transactionList.size();
    }

    public void add(Transaction transaction) {
        if (this.contains(transaction)) {
            throw new IllegalArgumentException();
        }

        transactionList.add(transaction);
    }

    public boolean contains(Transaction transaction) {
        return contains(transaction.getId());
    }

    public boolean contains(int id) {
        for (Transaction transaction : transactionList) {
            if (transaction.getId() == id) {
                return true;
            }
        }

        return false;
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }

        for (Transaction transaction : transactionList) {
            if (transaction.getId() == id) {
                transaction.setStatus(newStatus);
            }
        }
    }

    public void removeTransactionById(int id) {
        for (Transaction transaction : transactionList) {
            if (transaction.getId() == id){
                transactionList.remove(transaction);
                return;
            }
        }

        throw new IllegalArgumentException();
    }

    public Transaction getById(int id) {
        for (Transaction transaction : transactionList) {
            if (transaction.getId() == id) {
                return transaction;
            }
        }

        throw new IllegalArgumentException();
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transaction.getStatus().equals(status)){
                transactions.add(transaction);
            }
        }

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return null;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return transactionList.listIterator();
    }
}
