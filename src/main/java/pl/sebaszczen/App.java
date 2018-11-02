package pl.sebaszczen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.repository.WeatherReposiory;

import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class App
{

    @Autowired
    WeatherReposiory weatherReposiory;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
