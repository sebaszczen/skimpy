package pl.sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sebaszczen.domain.weather.WeatherStation;

public interface WeatherInterface extends JpaRepository<WeatherStation,Long> {
}
