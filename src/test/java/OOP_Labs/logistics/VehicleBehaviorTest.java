package OOP_Labs.logistics;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleBehaviorTest {

    @Test
    void cargosAreSortedByWeightInManifestStorage() {
        Truck truck = new Truck("AA1111BB", 100.0f);
        truck.addCargo(new StandardCargo("Steel", 20.0f));
        truck.addCargo(new FragileCargo("Glass", 5.0f));

        List<Cargo> sorted = truck.loadedCargos.toSortedList();

        assertEquals(2, sorted.size());
        assertEquals("Glass", sorted.get(0).getName());
        assertEquals("Steel", sorted.get(1).getName());
    }

    @Test
    void removeCargoByNameRemovesExistingCargo() {
        Truck truck = new Truck("AA2222CC", 100.0f);
        truck.addCargo(new StandardCargo("Boxes", 10.0f));
        truck.addCargo(new StandardCargo("Pipes", 15.0f));

        Cargo removed = truck.removeCargoByName("boxes");

        assertNotNull(removed);
        assertEquals("Boxes", removed.getName());
        assertEquals(1, truck.loadedCargos.toSortedList().size());
    }

    @Test
    void removeCargoByNameReturnsNullWhenMissing() {
        Truck truck = new Truck("AA3333DD", 100.0f);
        truck.addCargo(new StandardCargo("Boxes", 10.0f));

        assertNull(truck.removeCargoByName("Missing"));
    }
}

