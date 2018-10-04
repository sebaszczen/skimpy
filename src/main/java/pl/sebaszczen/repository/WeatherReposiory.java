package pl.sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sebaszczen.domain.weather.WeatherStation;

import java.util.List;

public interface WeatherReposiory extends JpaRepository<WeatherStation,Long> {
    boolean existsByHour(String godzina_pomiaru);

     List<WeatherStation> findByStacja(String stacja);
}
