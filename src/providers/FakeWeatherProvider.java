package providers;
import com.google.gson.JsonObject;
import misc.WeatherInfo;

import java.net.URL;
import java.util.Date;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michal
 */
public class FakeWeatherProvider implements IWeatherProvider{

    @Override
    public WeatherInfo GetWeatherNow(JsonObject jRoot) {
        WeatherInfo wi = new WeatherInfo();
        wi.description =  "POGOda uansuiond\nsadnaiosd\n\t xd";
        return  wi;
    }

    @Override
    public WeatherInfo GetWeatherHourly(JsonObject jRoot) {
        return null;
    }

    @Override
    public WeatherInfo GetWeatherDaily(JsonObject jRoot) {
        return null;
    }

    @Override
    public URL GetWeatherURL(double a, double b) {
        return null;
    }



    @Override
    public String GetHeader(JsonObject json, String type) {
        return null;
    }

    @Override
    public JsonObject getRootJson(double lat, double lon) {
        return null;
    }
}
