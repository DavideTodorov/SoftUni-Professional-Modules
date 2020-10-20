package christmas;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private String color;
    private int capacity;
    private List<Present> data;

    public Bag(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getColor() {
        return this.color;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int count() {
        return this.data.size();
    }

    public void add(Present present) {
        if (this.data.size() < capacity) {
            this.data.add(present);
        }
    }

    public boolean remove(String name) {
        boolean isRemoved = false;
        for (Present currPresent : this.data) {
            if (currPresent.getName().equals(name)) {
                this.data.remove(currPresent);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    public Present heaviestPresent() {
        double maxWeight = Double.MIN_VALUE;
        Present presentToReturn = null;

        for (Present currPresent : data) {
            if (currPresent.getWeight() > maxWeight) {
                maxWeight = currPresent.getWeight();
                presentToReturn = currPresent;
            }
        }

        return presentToReturn;
    }

    public Present getPresent(String name) {
        Present presentToReturn = null;
        for (Present currPresent : data) {
            if (currPresent.getName().equals(name)) {
                presentToReturn = currPresent;
                break;
            }
        }

        return presentToReturn;
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                sb.append(data.get(i));
            } else {
                sb.append(data.get(i)).append(System.lineSeparator());
            }
        }
        String color = this.color.substring(0, 1).toUpperCase() + this.color.substring(1);
        return String.format("%s bag contains:\n%s", color, sb.toString());
    }
}