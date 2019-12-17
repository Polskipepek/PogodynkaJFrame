package providers;
import misc.WeatherInfo;
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
    public WeatherInfo GetWeatherNow(double lat, double lon) {
        WeatherInfo wi = new WeatherInfo();
        wi.description =  "uansuiond\nsadnaiosd\n\t xd";
        return  wi;
    }

    @Override
    public WeatherInfo GetWeatherHours(double lat, double lon) {
        return null;
    }

    @Override
    public WeatherInfo GetWeatherDaily(double lat, double lon) {
        return null;
    }
}
