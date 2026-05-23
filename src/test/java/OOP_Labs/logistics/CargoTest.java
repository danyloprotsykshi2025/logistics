package OOP_Labs.logistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CargoTest {

    @Test
    void compareToOrdersByWeight() {
        Cargo light = new StandardCargo("Paper", 5.0f);
        Cargo heavy = new FragileCargo("Glass", 12.5f);

        assertTrue(light.compareTo(heavy) < 0);
        assertTrue(heavy.compareTo(light) > 0);
        assertEquals(0, light.compareTo(new StandardCargo("Paper2", 5.0f)));
    }

    @Test
    void toStringIncludesNameWeightAndType() {
        Cargo cargo = new FragileCargo("Vase", 2.5f);

        assertEquals("Vase (2.5 kg) - [Обережно! Крихке!]", cargo.toString());
    }
}

