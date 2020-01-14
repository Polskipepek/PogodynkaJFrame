package providers;
import com.google.gson.JsonObject;
import misc.WeatherInfo;/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.Date;

/**
 *
 * @author Michal
 */
public interface IWeatherProvider {

    WeatherInfo GetWeatherNow(JsonObject jRoot);

    WeatherInfo GetWeatherHourly(JsonObject jRoot);

    WeatherInfo GetWeatherDaily(JsonObject jRoot);
    URL GetWeatherURL(double a, double b);
    String GetHeader(JsonObject json, String type);
    JsonObject getRootJson(double lat, double lon);
}
