package OOP_Labs.logistics;

public class Truck extends Vehicle {

    public Truck(String plate, float capacity) { super(plate, capacity); }

    @Override
    public float calculateFuel(Destination destination){
        return (destination.getDistance() / 1000.0f) * 0.35f;
    }

}
