package garage;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GarageTests {
    private Garage garage;
    private Car car1;
    private Car car2;

    @Before
    public void setUp() {
        this.garage = new Garage();
        this.car1 = new Car("car1", 100, 5000);
        this.car2 = new Car("car2", 150, 10000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCar() {
        garage.addCar(null);
    }

    @Test
    public void testAddValidCar() {
        garage.addCar(car1);
        List<Car> cars = garage.getCars();
        List<Car> expected = Collections.singletonList(car1);
        assertEquals(expected, cars);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetAllCarsInUnmodifiableCollection() {
        garage.addCar(car1);
        garage.addCar(car2);
        List<Car> cars = garage.getCars();
        cars.remove(0);
    }

    @Test
    public void testGetAllCars() {
        garage.addCar(car1);
        garage.addCar(car2);
        List<Car> cars = garage.getCars();
        List<Car> expected = Arrays.asList(car1, car2);
        assertEquals(expected, cars);
    }

    @Test
    public void testGetCount() {
        garage.addCar(car1);
        garage.addCar(car2);
        int count = garage.getCount();
        assertEquals(2, count);
    }

    @Test
    public void testFindAllCarsWithSpeedAbove() {
        Car car3 = new Car("car3", 200, 15000);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        List<Car> cars = garage.findAllCarsWithMaxSpeedAbove(100);
        List<Car> expected = Arrays.asList(car2, car3);
        assertEquals(expected, cars);
    }

    @Test
    public void testGetTheMostExpensiveCar() {
        garage.addCar(car1);
        garage.addCar(car2);
        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();
        assertEquals(car2, theMostExpensiveCar);
    }

    @Test
    public void testGetTheMostExpensiveCarFromEmptyList() {
        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();
        assertNull(theMostExpensiveCar);
    }

    @Test
    public void testGetAllCarsByBrand() {
        Car car3 = new Car("car1", 200, 15000);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        List<Car> carsFound = garage.findAllCarsByBrand("car1");
        List<Car> expected = Arrays.asList(car1, car3);
        assertEquals(expected, carsFound);
    }
}