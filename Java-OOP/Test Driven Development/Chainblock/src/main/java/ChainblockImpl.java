import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

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
            if (transaction.getId() == id) {
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
            if (transaction.getStatus().equals(status)) {
                transactions.add(transaction);
            }
        }

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        LinkedHashSet<String> senders = transactionList.stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .map(Transaction::getSender)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (senders.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        LinkedHashSet<String> receivers = transactionList.stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .map(Transaction::getReceiver)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (receivers.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return transactionList.stream()
                .sorted((t1, t2) -> {
                    int compare = Double.compare(t2.getAmount(), t1.getAmount());

                    if (compare == 0) {
                        compare = Integer.compare(t1.getId(), t2.getId());
                    }

                    return compare;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        LinkedHashSet<Transaction> transactions = transactionList.stream()
                .filter(t -> t.getSender().equals(sender))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return transactions;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        LinkedHashSet<Transaction> transactions = transactionList.stream()
                .filter(t -> t.getReceiver().equals(receiver))
                .sorted((t1, t2) -> {
                    int compare = Double.compare(t2.getAmount(), t1.getAmount());

                    if (compare == 0) {
                        compare = Integer.compare(t1.getId(), t2.getId());
                    }

                    return compare;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (transactions.isEmpty()){
            throw new IllegalArgumentException();
        }

        return transactions;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return transactionList.stream()
                .filter(t -> t.getStatus().equals(status) && t.getAmount() <= amount)
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        LinkedHashSet<Transaction> transactions = transactionList.stream()
                .filter(t -> t.getSender().equals(sender) && t.getAmount() > amount)
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (transactions.isEmpty()){
            throw new IllegalArgumentException();
        }

        return transactions;
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
