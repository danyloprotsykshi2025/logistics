package OOP_Labs.logistics;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logistics")
@CrossOrigin(origins = "*")
@Tag(name = "Логістичний API", description = "Управління маршрутами та складом через REST")
public class LogisticsController {

    private final LogisticsService logisticsService;

    public LogisticsController(LogisticsService logisticsService) {
        this.logisticsService = logisticsService;
    }

    @GetMapping("/destinations")
    @Operation(summary = "Отримати всі доступні міста та маршрути")
    public List<Destination> getDestinations() {
        return logisticsService.getAllDestinations();
    }

    @GetMapping("/cargo")
    @Operation(summary = "Переглянути всі вантажі на складі")
    public List<Cargo> getWarehouse() {
        return logisticsService.getAllCargo();
    }

    @DeleteMapping("/cargo/{id}")
    @Operation(summary = "Видалити вантаж (списати зі складу)")
    public void deleteCargo(@PathVariable Long id) {
        logisticsService.deleteCargo(id);
    }

    @PutMapping("/cargo/{id}/assign")
    @Operation(summary = "Призначити існуючому вантажу нове місто")
    public Cargo assignDestination(
            @PathVariable Long id,
            @RequestParam Long destinationId) {
        return logisticsService.assignCargoToDestination(id, destinationId);
    }

    @PostMapping("/cargo/standard")
    @Operation(summary = "Створити новий стандартний вантаж та призначити його місту")
    public Cargo addStandardCargo(
            @RequestParam String name,
            @RequestParam float weight,
            @RequestParam Long destinationId) {
        return logisticsService.createStandardCargoForDestination(name, weight, destinationId);
    }

    @PostMapping("/dispatch")
    @Operation(summary = "Сформувати рейс: завантажити вантажі у машину та розрахувати пальне")
    public String dispatch(@RequestParam Long destinationId, @RequestParam String vehicleType) {
        return logisticsService.dispatchVehicleForDestination(destinationId, vehicleType);
    }

    @PostMapping("/destinations")
    @Operation(summary = "Додати нове місто до маршрутної сітки")
    public Destination addDestination(
            @RequestParam String cityName,
            @RequestParam int distance) {
        return logisticsService.createDestination(cityName, distance);
    }
}