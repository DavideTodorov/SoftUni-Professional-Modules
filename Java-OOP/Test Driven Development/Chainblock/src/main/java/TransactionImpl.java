public class TransactionImpl implements Transaction {

    private int id;
    private TransactionStatus status;
    private String from;
    private String to;
    private double amount;

    public TransactionImpl(int id, TransactionStatus status, String from, String to, double amount) {
        this.id = id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public int compareTo(TransactionImpl other) {
        return 0;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public TransactionStatus getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(TransactionStatus newStatus) {
        this.status = newStatus;
    }

    @Override
    public String getSender(){
        return this.from;
    }

    @Override
    public Double getAmount(){
        return this.amount;
    }

    @Override
    public String getReceiver(){
        return this.to;
    }
}
