package OOP_Labs.logistics;

public class Van extends Vehicle {

    public Van(String plate, float capacity) { super(plate, capacity); }

    @Override
    public float calculateFuel(Destination destination){
        return (destination.getDistance() / 1000.0f) * 0.12f;
    }

}
