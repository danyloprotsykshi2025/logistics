package OOP_Labs.logistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationRouteUtilsTest {

    @Test
    void formatDistanceInfoUsesKilometers() {
        Destination destination = new Destination("Kyiv", 1500);

        String result = Destination.RouteUtils.formatDistanceInfo(destination);

        assertEquals("Маршрут затверджено. Напрямок: Kyiv | Дистанція: 1.5 км.", result);
    }

    @Test
    void isValidRouteRejectsNullOrNonPositiveDistance() {
        Destination destination = new Destination("Lviv", 0);

        assertFalse(Destination.RouteUtils.isValidRoute(null));
        assertFalse(Destination.RouteUtils.isValidRoute(destination));
        assertTrue(Destination.RouteUtils.isValidRoute(new Destination("Odesa", 100)));
    }
}

