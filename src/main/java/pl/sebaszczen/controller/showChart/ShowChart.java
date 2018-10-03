package pl.sebaszczen.controller.showChart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import pl.sebaszczen.domain.weather.WeatherStation;
import pl.sebaszczen.repository.WeatherInterface;

import java.util.List;

public class ShowChart {
    @Autowired
    WeatherInterface weatherInterface;

    @GetMapping("showchart")
    public String getchart(Model model) {
        List<WeatherStation> all = weatherInterface.findAll();
        Object[][] table = new Object[3][all.size()];

        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {

            }
        }

        return "chart";
    }

    public WeatherStation[] getData() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://danepubliczne.imgw.pl/api/data/synop";
        WeatherStation[] forObject = restTemplate.getForObject(url, WeatherStation[].class);
        return forObject;
    }
}
