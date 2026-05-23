package OOP_Labs.logistics;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STANDARD")
public class StandardCargo extends Cargo {

    public StandardCargo() {}

    public StandardCargo(String name, float weight){
        super(name, weight);
    }

    @Override
    public String getCargoType() {
        return "Звичайний вантаж";
    }
}