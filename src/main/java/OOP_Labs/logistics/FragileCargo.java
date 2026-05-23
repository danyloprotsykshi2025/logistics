package OOP_Labs.logistics;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FRAGILE")
public class FragileCargo extends Cargo {

    public FragileCargo() {}

    public FragileCargo(String name, float weight){
        super(name, weight);
    }

    @Override
    public String getCargoType(){
        return "Обережно! Крихке!";
    }
}