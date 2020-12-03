public class Main {

    public static void main(String[] args) {
        ModifyPrice modifyPrice = new ModifyPrice();
        Product product = new Product("Phone", 1000);

        execute(modifyPrice, new DecreaseProductPriceCommand(product, 100));
        execute(modifyPrice, new IncreaseProductPriceCommand(product, 200));

        System.out.println(product.toString());

    }

    private static void execute(ModifyPrice modifyPrice, Command productCommand) {
        modifyPrice.setCommand(productCommand);
        modifyPrice.invoke();
    }
}
