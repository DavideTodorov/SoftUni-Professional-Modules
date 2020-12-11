package onlineShop.models.products.components;

public class RandomAccessMemory extends BaseComponent {
    protected RandomAccessMemory(int id, String manufacturer, String model, double price,
                                 double overallPerformance, int generation) {
        super(id, manufacturer, model, price,
                1.20 *overallPerformance, generation);
    }
}
