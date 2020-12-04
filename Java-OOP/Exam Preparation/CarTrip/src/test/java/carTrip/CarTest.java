package carTrip;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    private Car car;

    @Before
    public void setUp() {
        car = new Car("Model", 100, 50, 1);
    }

    @Test
    public void testCreateCar() {
        car = new Car("Model_Test", 1000, 50, 2);
        assertEquals("Model_Test", car.getModel());
        assertEquals(1000, car.getTankCapacity(), 0);
        assertEquals(50, car.getFuelAmount(), 0);
        assertEquals(2, car.getFuelConsumptionPerKm(), 0);
    }

    @Test
    public void setValidModel() {
        String model = "Citroen";
        car.setModel(model);
        assertEquals(model, car.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullModel(){
        car.setModel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void seEmptyModel(){
        car.setModel("");
    }

    @Test
    public void testSetTankCapacity(){
        double newCapacity = 123.2;
        car.setTankCapacity(newCapacity);
        assertEquals(newCapacity, car.getTankCapacity(), 0);
    }

    @Test
    public void setValidQuantityOfFuel(){
        double fuel = 99.3;
        car.setFuelAmount(fuel);
        assertEquals(fuel, car.getFuelAmount(),0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidQuantityOfFuel(){
        double fuel = 100.1;
        car.setFuelAmount(fuel);
    }

    @Test
    public void testDriveValidDistance(){
        double distance = 49;
        car.drive(distance);
        assertEquals(1, car.getFuelAmount(),0.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testDriveInvalidDistance(){
        double distance = 50.1;
        car.drive(distance);
    }

    @Test
    public void testRefuelWithValidQuantity(){
        car.refuel(40);
        assertEquals(90,car.getFuelAmount(),0);
    }

    @Test(expected = IllegalStateException.class)
    public void testRefuelWithInvalidQuantity(){
        car.refuel(51);
    }
}