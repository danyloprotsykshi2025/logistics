package OOP_Labs.logistics;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private int distance;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cargo> cargos;

    public Destination() {
    }

    public Destination(String cityName, int distance){
        this.cityName = cityName;
        this.distance = distance;
    }

    public List<Cargo> getCargos() { return cargos; }
    public void setCargos(List<Cargo> cargos) { this.cargos = cargos; }

    public Long getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public String getCityName() {
        return cityName;
    }

    public static class RouteUtils {
        public static String formatDistanceInfo(Destination dest){
            float km = dest.getDistance() / 1000.0f;
            return "Маршрут затверджено. Напрямок: " + dest.getCityName() + " | Дистанція: " + km + " км.";
        }

        public static boolean isValidRoute(Destination dest){
            return dest != null && dest.getDistance() > 0;
        }
    }
}