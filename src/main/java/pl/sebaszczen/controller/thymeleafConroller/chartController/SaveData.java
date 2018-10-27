package pl.sebaszczen.controller.thymeleafConroller.chartController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sebaszczen.repository.WeatherReposiory;

import java.util.logging.Logger;

@Component
public class SaveData {

    @Autowired
    private WeatherReposiory weatherReposiory;

    Logger logger = Logger.getLogger(SaveData.class.getName());

//    @PostConstruct
//    @Scheduled(cron = "0 0/60 10-16 * * *")
//    public void saveData() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://danepubliczne.imgw.pl/api/data/synop";
//        WeatherStation[] forObject = restTemplate.getForObject(url, WeatherStation[].class);
//        System.out.println(forObject[1].getHour());
//        if (!weatherReposiory.existsByHour(forObject[1].getHour())) {
//            for (WeatherStation weatherStation : forObject) {
//                weatherReposiory.save(weatherStation);
//            }
//        }
//    }

}
