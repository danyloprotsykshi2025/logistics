package OOP_Labs.logistics;
// I
public interface Transportable {

    boolean addCargo(Cargo cargo);
    void printCargoManifest();
    Cargo removeCargoByName(String cargoName);
}
