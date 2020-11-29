public interface Transaction extends Comparable<TransactionImpl>{

    int getId();

    TransactionStatus getStatus();

    void setStatus(TransactionStatus newStatus);

    String getSender();

    Double getAmount();

    String getReceiver();
}
