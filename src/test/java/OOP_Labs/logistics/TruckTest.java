package OOP_Labs.logistics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {

    @Test
    public void testAddCargoPositiveScenario() {
        Truck truck = new Truck("AA9999ВС", 10000.0f);
        Cargo normalCargo = new StandardCargo("Bricks", 5000.0f);

        boolean isAdded = truck.addCargo(normalCargo);

        assertTrue(isAdded, "Вантаж мав успішно додатися, бо місця достатньо");
    }

    @Test
    public void testAddCargoNegativeScenario() {
        Truck truck = new Truck("AA9999ВС", 10000.0f);
        Cargo heavyCargo = new StandardCargo("Titanium", 15000.0f);

        boolean isAdded = truck.addCargo(heavyCargo);

        assertFalse(isAdded, "Вантаж не мав додатися, бо перевищено ліміт ваги");
    }
}