package pl.sebaszczen.domain.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
//@JsonClassDescription
public class WeatherStation {
    @Id
    @GeneratedValue
    Long id;
    private Long id_stacji;
    private String stacja;
    private Calendar data_pomiaru;
    @JsonProperty(value = "godzina_pomiaru")
    private String hour;
    private float temperatura;
    private float predkosc_wiatru;
    private int kierunek_wiatru;
    private float wilgotnosc_wzgledna;
    private float suma_opadu;
    private float cisnienie;

    public WeatherStation() {
    }

    public WeatherStation(Long id_stacji, float temperatura, float predkosc_wiatru, Calendar data_pomiaru) {
        this.id_stacji = id_stacji;
        this.temperatura = temperatura;
        this.predkosc_wiatru = predkosc_wiatru;
        this.data_pomiaru = data_pomiaru;
    }

    public Long getId_stacji() {
        return id_stacji;
    }

    public String getStacja() {
        return stacja;
    }


    public String getHour() {
        return hour;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public float getPredkosc_wiatru() {
        return predkosc_wiatru;
    }

    public int getKierunek_wiatru() {
        return kierunek_wiatru;
    }

    public void setData_pomiaru(Calendar data_pomiaru) {
        this.data_pomiaru = data_pomiaru;
    }

    public Calendar getData_pomiaru() {
        return data_pomiaru;
    }

    public float getWilgotnosc_wzgledna() {
        return wilgotnosc_wzgledna;
    }

    public float getSuma_opadu() {
        return suma_opadu;
    }

    public float getCisnienie() {
        return cisnienie;
    }

    public void setId_stacji(Long id_stacji) {
        this.id_stacji = id_stacji;
    }

    public void setStacja(String stacja) {
        this.stacja = stacja;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setPredkosc_wiatru(float predkosc_wiatru) {
        this.predkosc_wiatru = predkosc_wiatru;
    }

    public void setKierunek_wiatru(int kierunek_wiatru) {
        this.kierunek_wiatru = kierunek_wiatru;
    }

    public void setWilgotnosc_wzgledna(float wilgotnosc_wzgledna) {
        this.wilgotnosc_wzgledna = wilgotnosc_wzgledna;
    }

    public void setSuma_opadu(float suma_opadu) {
        this.suma_opadu = suma_opadu;
    }

    public void setCisnienie(float cisnienie) {
        this.cisnienie = cisnienie;
    }

    @Override
    public String toString() {
        return "WeatherStation{" +
                "id_stacji=" + id_stacji +
                ", stacja='" + stacja + '\'' +
                ", data_pomiaru='" + data_pomiaru + '\'' +
                ", hour='" + hour + '\'' +
                ", temperatura=" + temperatura +
                ", predkosc_wiatru=" + predkosc_wiatru +
                ", kierunek_wiatru=" + kierunek_wiatru +
                ", wilgotnosc_wzgledna=" + wilgotnosc_wzgledna +
                ", suma_opadu=" + suma_opadu +
                ", cisnienie=" + cisnienie +
                '}';
    }
}
