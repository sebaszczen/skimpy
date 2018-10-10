package pl.sebaszczen.controller.showChart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sebaszczen.domain.weather.WeatherStation;
import pl.sebaszczen.repository.WeatherReposiory;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ShowChart {
    @Autowired
    WeatherReposiory weatherReposiory;

    @GetMapping("showchart")
    public String getchart(Model model) {
        List<WeatherStation> all = weatherReposiory.findByStacja("Chojnice");

        Object[][] table = new Object[all.size()+1][2];
        table[0][0] = "year";
        table[0][1] = "temperature";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        for (int row = 1; row < all.size()+1; row++) {
                table[row][0]=simpleDateFormat.format(all.get(row-1).getData_pomiaru().getTime())+" "+all.get(row-1).getHour();
                table[row][1]= all.get(row-1).getTemperatura();
        }

        model.addAttribute("dataList", table);
        return "chart";
    }


}
