package OOP_Labs.logistics;

import org.springframework.stereotype.Service;
import java.util.List;
                                    // патерн Фасад
@Service
public class LogisticsService {

    private final CargoRepository cargoRepository;
    private final DestinationRepository destinationRepository;

    public LogisticsService(CargoRepository cargoRepository, DestinationRepository destinationRepository) {
        this.cargoRepository = cargoRepository;
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public List<Cargo> getAllCargo() {
        return cargoRepository.findAll();
    }

    public void deleteCargo(Long id) {
        cargoRepository.deleteById(id);
    }

    public Cargo assignCargoToDestination(Long cargoId, Long destinationId) {
        Cargo cargo = cargoRepository.findById(cargoId)
                .orElseThrow(() -> new RuntimeException("Вантаж з ID " + cargoId + " не знайдено"));

        Destination dest = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Місто з ID " + destinationId + " не знайдено"));

        cargo.setDestination(dest);
        return cargoRepository.save(cargo);
    }

    public Cargo createStandardCargoForDestination(String name, float weight, Long destinationId) {
        Destination dest = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Місто з ID " + destinationId + " не знайдено"));

        StandardCargo cargo = new StandardCargo(name, weight);
        cargo.setDestination(dest);
        return cargoRepository.save(cargo);
    }

    public Destination createDestination(String cityName, int distance) {
        Destination dest = new Destination(cityName, distance);

        return destinationRepository.save(dest);
    }

    public String dispatchVehicleForDestination(Long destinationId, String vehicleType) {
        Destination dest = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Місто не знайдено"));

        List<Cargo> cargosToLoad = dest.getCargos();

        if (cargosToLoad == null || cargosToLoad.isEmpty()) {
            return "Для міста " + dest.getCityName() + " немає вантажів на складі.";
        }

        Vehicle currentVehicle = VehicleFactory.createVehicle(vehicleType);
        if (currentVehicle == null) { // фабрика
            return "Помилка: невідомий тип транспорту.";
        }

        java.util.List<Cargo> loadedCargos = new java.util.ArrayList<>();
        int successCount = 0;

        for (Cargo cargo : cargosToLoad) {
            if (currentVehicle.addCargo(cargo)) {
                successCount++;
                loadedCargos.add(cargo);
            }
        }

        cargoRepository.deleteAll(loadedCargos);
        float fuel = currentVehicle.calculateFuel(dest);

        return String.format(
                "Рейс до %s підготовлено. Використано: %s. Завантажено: %d вантажів. Пальне: %.2f л.",
                dest.getCityName(), vehicleType, successCount, fuel
        );
    }
}