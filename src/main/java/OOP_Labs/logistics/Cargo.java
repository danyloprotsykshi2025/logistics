package OOP_Labs.logistics;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cargo_type")
public abstract class Cargo implements Comparable<Cargo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float weight;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public Cargo() {}

    public Cargo(String name, float weight){
        this.name = name;
        this.weight = weight;
    }

    public Long getId() { return id; }
    public abstract String getCargoType();

    @Override
    public int compareTo(Cargo other) {
        return Float.compare(this.weight, other.weight);
    }

    public Destination getDestination() { return destination; }
    public void setDestination(Destination destination) { this.destination = destination; }

    @Override
    public String toString(){
        return name + " (" + weight + " kg) - [" + getCargoType() + "]";
    }

    public String getName() { return name; }
    public float getWeight() { return weight; }
}