package providers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.JsonArray;
import misc.Utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import misc.WeatherInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


/**
 * @author Michal
 */
public class DarkSkyWeatherProvider implements IWeatherProvider {

    double[] latlon = null;

    public URL GetWeatherURL(double latitude, double longtitude) {
        try {
            return new URL(" https://api.darksky.net/forecast/" + Utils.Utilities.keyWeather + "/" + latitude + "," + longtitude + "?lang=" + Utils.Utilities.getLanguageCode() + "&units=si");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public JsonObject PobierzPogodeJSON(URL url) throws IOException {
        //System.out.println(url);

        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobjs = root.getAsJsonObject();
        //JsonObject results = rootobjs.get("results").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject("annotations").getAsJsonObject("DMS");

        //System.out.println("PobierzJSON: " + rootobjs);
        return rootobjs;
    }

    public String GetHeader(JsonObject json, String type) {
        String podsumowanie = json.get("currently").getAsJsonObject().get(type).getAsString();
        return podsumowanie;
    }

    public String GetWeatherDescriptionNow(JsonObject json) {
        int godzinatmp = json.get("time").getAsInt();
        //Icon icon = json.get("icon").getAsJsonObject();
        float temperatura = json.get("temperature").getAsFloat();
        float odczuwalna = json.get("apparentTemperature").getAsFloat();
        float cisnienie = json.get("pressure").getAsFloat();
        float predkoscWiatru = json.get("windSpeed").getAsFloat();
        float widocznosc = json.get("visibility").getAsFloat();

        //java.util.Date data = new java.util.Date((long) godzinatmp * 1000);
        return "Temperatura: " + temperatura + "°C, \nTemperatura odczuwalna: " + odczuwalna + "°C, \nCisnienie: " + cisnienie + " hPa, \nPredkosc wiatru: " + predkoscWiatru + " km/h, \nWidocznosc: " + widocznosc + " km\n";
    }

    public String GetWeatherDescriptionHourly(JsonArray json) {
        String temp = "";
        for (JsonElement j : json) {
            temp += new Date((long) j.getAsJsonObject().get("time").getAsFloat() * 1000) + "\nPodsumowanie: " + j.getAsJsonObject().get("summary").getAsString()+"\n"+ GetWeatherDescriptionNow(j.getAsJsonObject()) + "\n\n";
        }
        return temp;
    }

    public String GetWeatherDescriptionDaily(JsonArray jsonA) {
        String string = "";
        for (JsonElement json : jsonA) {
            //Icon icon = json.get("icon").getAsJsonObject();
            json = json.getAsJsonObject();
            float godzinatmp = ((JsonObject) json).get("time").getAsFloat();
            float wschod = ((JsonObject) json).get("sunriseTime").getAsFloat();
            float zachod = ((JsonObject) json).get("sunsetTime").getAsFloat();
            float temperaturaMax = ((JsonObject) json).get("temperatureHigh").getAsFloat();
            float temperaturaMin = ((JsonObject) json).get("temperatureLow").getAsFloat();
            float zakryteNiebo = ((JsonObject) json).get("cloudCover").getAsFloat();
            float cisnienie = ((JsonObject) json).get("pressure").getAsFloat();
            float predkoscWiatru = ((JsonObject) json).get("windSpeed").getAsFloat();
            float widocznosc = ((JsonObject) json).get("visibility").getAsFloat();

            Date data = new Date((long) godzinatmp * 1000);
            Date wschodD = new Date((long) wschod * 1000);
            Date zachodD = new Date((long) zachod * 1000);


            string += data + "\nWschod: " + wschodD + "\nZachod: " + zachodD + "\nTemperatura maksymalna: " + temperaturaMax + "°C,\nTemperatura minimalna: " + temperaturaMin
                    + "°C, \nNiebo zakryte chmurami: " + zakryteNiebo * 100f + "%, \nCisnienie: " + cisnienie + " hPa, \nPredkosc wiatru: " + predkoscWiatru + " km/h, \nWidocznosc: " + widocznosc + " km\n\n";

        }
        return string;
    }

    public void Init(URL pogURL) {
        System.out.println(pogURL);
    }

    @Override
    public WeatherInfo GetWeatherNow(JsonObject jRoot) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.description = "Podsumowanie: " + GetWeatherDescriptionNow(jRoot.getAsJsonObject().getAsJsonObject("currently"));
        weatherInfo.imageName = jRoot.getAsJsonObject().get("currently").getAsJsonObject().get("icon") + ".svg";
        //weatherInfo.header = GetHeader(jRoot.getAsJsonObject().getAsJsonObject("currently"));
        return weatherInfo;
    }

    @Override
    public WeatherInfo GetWeatherHourly(JsonObject jRoot) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.description = GetWeatherDescriptionHourly(jRoot.getAsJsonObject().getAsJsonObject("hourly").get("data").getAsJsonArray());
        weatherInfo.imageName = jRoot.getAsJsonObject().get("hourly").getAsJsonObject().get("icon").getAsString() + ".jpg";
        return weatherInfo;
    }

    @Override
    public WeatherInfo GetWeatherDaily(JsonObject jRoot) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.description = GetWeatherDescriptionDaily(jRoot.getAsJsonObject().getAsJsonObject("daily").get("data").getAsJsonArray());
        weatherInfo.imageName = jRoot.getAsJsonObject().get("daily").getAsJsonObject().get("icon").getAsString() + ".jpg";
        return weatherInfo;
    }

    public JsonObject getRootJson(double lat, double lon) {
        JsonObject jRoot = null;
        try {
            jRoot = PobierzPogodeJSON(GetWeatherURL(lat, lon));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jRoot);
        return jRoot;
    }
}