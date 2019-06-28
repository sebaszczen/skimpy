package pl.sebaszczen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
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
@EnableCaching
public class App
{

    @Autowired
    WeatherReposiory weatherReposiory;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("ticket");
    }
}
