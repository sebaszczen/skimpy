package pl.sebaszczen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sebaszczen.domain.weather.WeatherStation;
import pl.sebaszczen.repository.WeatherInterface;

import java.util.Date;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{

    @Autowired
    WeatherInterface weatherInterface;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        WeatherStation weatherStation = new WeatherStation(1L, 10, 20,new Date(2018,2,22));
        WeatherStation weatherStation1 = new WeatherStation(2L, 10, 20,new Date(2018,2,30));
        WeatherStation weatherStation2 = new WeatherStation(3L, 10, 20,new Date(2018,3,2));

        weatherInterface.save(weatherStation);
        weatherInterface.save(weatherStation1);
        weatherInterface.save(weatherStation2);
    }
}
