package rabbits;

import java.util.ArrayList;
import java.util.List;

public class Cage {

    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void add (Rabbit rabbit) {
        if (this.data.size() < this.capacity) {
            boolean isPresent = false;

            for (Rabbit datum : data) {
                if (datum.getName().equals(rabbit.getName())) {
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                this.data.add(rabbit);
            }
        }
    }

    public boolean removeRabbit(String name) {
        return this.data.removeIf(rabbit -> (rabbit.getName().equals(name)));
    }

    public void removeSpecies(String species) {
        this.data.removeIf(rabbit -> rabbit.getSpecies().equals(species));
    }

    public Rabbit sellRabbit(String name) {
        Rabbit rabbitToReturn = null;

        for (Rabbit rabbit : this.data) {
            if (rabbit.getName().equals(name)) {
                if (rabbit.isAvailable()) {
                    rabbit.setAvailable();
                    rabbitToReturn = rabbit;
                    break;
                }
            }
        }

        return rabbitToReturn;
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {
        List<Rabbit> rabbitList = new ArrayList<>();

        for (Rabbit rabbit : this.data) {
            if (rabbit.getSpecies().equals(species)) {
                if (rabbit.isAvailable()) {
                    rabbit.setAvailable();
                    rabbitList.add(rabbit);
                }
            }
        }

        return rabbitList;
    }

    public int count() {
        return this.data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder()
                .append(String.format("Rabbits available at %s:", this.name))
                .append(System.lineSeparator());

        for (Rabbit rabbit : this.data) {
            if (rabbit.isAvailable()) {
                sb.append(rabbit.toString()).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}