package OOP_Labs.logistics;

public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        if (type.equalsIgnoreCase("TRUCK")) {
            return new Truck("AA1111BB", 10000.0f);
        } else if (type.equalsIgnoreCase("VAN")) {
            return new Van("BC2222CB", 3000.0f);
        }
        throw new IllegalArgumentException("Невідомий тип транспорту: " + type);
    }
}