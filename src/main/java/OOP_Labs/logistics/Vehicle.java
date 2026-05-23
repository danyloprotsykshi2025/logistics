package OOP_Labs.logistics;
import java.util.List;

public abstract class Vehicle implements Transportable {

    private class Engine{
        private boolean isRunning = false;

        public void start(){
            if(!isRunning){
                isRunning = true;
                System.out.println("Двигун машини " + plate + " запущено");
            } else{
                System.out.println("Двигун не працює!");
            }
        }

        public boolean isRunning(){
            return isRunning;
        }
    }

    private Engine engine = new Engine();
    public void startEngine(){
        engine.start();
    }

    public boolean isReadyToDepart(){
        return engine.isRunning();
    }

    private String plate;
    private float capacity;
    protected CustomBinaryTree<Cargo> loadedCargos;

    public Vehicle(String plate, float capacity){
        this.plate = plate;
        this.capacity = capacity;
        this.loadedCargos = new CustomBinaryTree<>();
    }

    public boolean addCargo(Cargo cargo){
        float currentWeight = 0;
        for(Cargo c : loadedCargos.toSortedList()) {
            currentWeight += c.getWeight();
        }

        if(currentWeight + cargo.getWeight() <= capacity){
            loadedCargos.add(cargo);
            System.out.println("Вантаж " + cargo.getName() + " успішно додано.");
            return true;
        } else {
            float available = capacity - currentWeight;
            System.out.println("Помилка! Немає місця для " + cargo.getName() + ". Доступно лише: " + available + " кг.");
            return false;
        }
    }

    public abstract float calculateFuel(Destination destination);

    public void printCargoManifest() {
        System.out.println("--- Вміст машини " + plate + " ---");
        if (loadedCargos.isEmpty()) {
            System.out.println("(Порожньо)");
        } else {
            List<Cargo> sortedCargos = loadedCargos.toSortedList();

            float totalWeight = 0;
            for (Cargo c : sortedCargos) {
                System.out.println(" - " + c.toString());
                totalWeight += c.getWeight();
            }
            System.out.println("Всього завантажено: " + totalWeight + " / " + capacity + " кг");
        }
        System.out.println("------------------------------");
    }

    public Cargo removeCargoByName(String cargoName) {
        for (Cargo c : loadedCargos.toSortedList()) {
            if (c.getName().equalsIgnoreCase(cargoName)) {
                float weight = c.getWeight();
                loadedCargos.remove(c);
                System.out.println("Вантаж '" + c.getName() + "' (" + weight + " кг) успішно вивантажено.");
                return c;
            }
        }
        System.out.println("Вантаж із назвою '" + cargoName + "' не знайдено!");
        return null;
    }

    public String getPlate() {
        return plate;
    }

    // Патерн Template Method
    public final String performDelivery(Destination dest) {
        StringBuilder report = new StringBuilder();

        startEngine();
        report.append("Двигун запущено. ");
        float fuelNeeded = calculateFuel(dest);
        report.append("Розраховано пального: ").append(fuelNeeded).append(" л. ");
        report.append("Відправлення до: ").append(dest.getCityName()); // Крок 3: Загальний

        return report.toString();
    }
}
