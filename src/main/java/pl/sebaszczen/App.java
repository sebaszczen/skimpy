package pl.sebaszczen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.sebaszczen.domain.weather.WeatherStation;
import pl.sebaszczen.repository.WeatherReposiory;

import java.util.GregorianCalendar;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class App
{

    @Autowired
    WeatherReposiory weatherReposiory;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

}
