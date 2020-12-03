public class Main {

    public static void main(String[] args) {
        SingletonCityContainer container = SingletonCityContainer.getInstance();

        container.increasePopulation("Plovdiv", 1000);

        System.out.println(container.getCapitalPopulation("Plovdiv"));
    }
}
