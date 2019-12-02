/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michal
 */
public interface IWeatherProvider {
    // String GetWeatherDescriptionDaily();

    WeatherInfo GetWeatherNow(double lat, double lon);

    WeatherInfo GetWeatherHours(double lat, double lon);

    WeatherInfo GetWeatherDaily(double lat, double lon);

}
