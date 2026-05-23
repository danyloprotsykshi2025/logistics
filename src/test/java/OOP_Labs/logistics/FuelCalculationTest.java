package OOP_Labs.logistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuelCalculationTest {

    @Test
    void truckFuelCalculationIsBasedOnDistance() {
        Truck truck = new Truck("AA4444EE", 100.0f);
        Destination destination = new Destination("Kharkiv", 10000);

        assertEquals(3.5f, truck.calculateFuel(destination), 0.0001f);
    }

    @Test
    void vanFuelCalculationIsBasedOnDistance() {
        Van van = new Van("AA5555FF", 100.0f);
        Destination destination = new Destination("Dnipro", 10000);

        assertEquals(1.2f, van.calculateFuel(destination), 0.0001f);
    }
}

