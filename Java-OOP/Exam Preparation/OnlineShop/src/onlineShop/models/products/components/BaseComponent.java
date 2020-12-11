package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

public class BaseComponent extends BaseProduct {
    private int generation;
    
    protected BaseComponent(int id, String manufacturer, String model,
                            double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public String toString() {
        return "BaseComponent{}";
    }
}