public class IncreaseProductPriceCommand implements Command {
    private Product product;
    private int amount;

    public IncreaseProductPriceCommand(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public void executeCommand() {
        int newPrice = this.product.getPrice() + amount;
        product.setPrice(newPrice);
    }
}