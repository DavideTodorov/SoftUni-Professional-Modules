package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;

public class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }


    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        }

        double sumFromComponents = components.stream()
                .mapToDouble(Component::getOverallPerformance)
                .average()
                .orElse(0);

        return super.getOverallPerformance() + sumFromComponents;
    }

    @Override
    public double getPrice() {
        double sumFromComponents = components.stream()
                .mapToDouble(Component::getPrice)
                .sum();

        double sumFromPeripheral = peripherals.stream()
                .mapToDouble(Peripheral::getPrice)
                .sum();

        return super.getPrice() + sumFromComponents + sumFromPeripheral;
    }

    @Override
    public void addComponent(Component component) {
        for (Component currComponent : components) {
            if (currComponent.getClass().getSimpleName().equals(component.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                        component.getClass().getSimpleName(),
                        this.getId()));
            }
        }

        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        boolean isRemoved = false;

        Component componentToReturn = null;

        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                componentToReturn = component;
                components.remove(component);
                isRemoved = true;
                break;
            }
        }

        if (!isRemoved) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType,
                    this.getClass().getSimpleName(),
                    this.getId()));
        }

        return componentToReturn;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral currPeripheral : peripherals) {
            if (currPeripheral.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                        peripheral.getClass().getSimpleName(),
                        this.getId()));
            }
        }

        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        boolean isRemoved = false;

        Peripheral peripheralToReturn = null;

        for (Peripheral peripheral : peripherals) {
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                peripheralToReturn = peripheral;
                peripherals.remove(peripheral);
                isRemoved = true;
                break;
            }
        }

        if (!isRemoved) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, peripheralType,
                    this.getClass().getSimpleName(),
                    this.getId()));
        }

        return peripheralToReturn;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append(System.lineSeparator());

        builder.append(String.format(" Components (%d):", components.size())).append(System.lineSeparator());
        for (Component component : components) {
            builder.append("  " + component.toString()).append(System.lineSeparator());
        }

        final double averagePerformance = peripherals.stream()
                .mapToDouble(Product::getOverallPerformance)
                .average()
                .orElse(0.0);

        builder.append(String.format(" Peripherals (%d); " +
                "Average Overall Performance (%.2f):", peripherals.size(), averagePerformance))
                .append(System.lineSeparator());

        for (Peripheral peripheral : peripherals) {
            builder.append("  " + peripheral.toString()).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
