package providers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        String podsumowanie = json.get(type).getAsJsonObject().get("summary").getAsString();
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
        return "\nPodsumowanie: " + json.get("summary").getAsString() + "\nTemperatura: " + temperatura + "°C, \nTemperatura odczuwalna: " + odczuwalna + "°C, \nCisnienie: " + cisnienie + " hPa, \nPredkosc wiatru: " + predkoscWiatru + " km/h, \nWidocznosc: " + widocznosc + " km\n";
    }

    public String GetWeatherDescriptionDaily(JsonObject json) {
        //Icon icon = json.get("icon").getAsJsonObject();
        String wschod = json.get("sunriseTime").getAsString();
        String zachod = json.get("sunsetTime").getAsString();
        float temperaturaMax = json.get("temperatureHigh").getAsFloat();
        float temperaturaMin = json.get("temperatureLow").getAsFloat();
        float zakryteNiebo = json.get("cloudCover").getAsFloat();
        float cisnienie = json.get("pressure").getAsFloat();
        float predkoscWiatru = json.get("windSpeed").getAsFloat();
        float widocznosc = json.get("visibility").getAsFloat();

        //java.util.Date data = new java.util.Date((long) godzinatmp * 1000);
        return "\nWschod: " + wschod + "\nZachod: " + zachod + "\nTemperatura maksymalna: " + temperaturaMax + "°C,\nTemperatura minimalna: " + temperaturaMin + "°C, \nNiebo zakryte chmurami: " + zakryteNiebo * 100f + "%, \nCisnienie: " + cisnienie + " hPa, \nPredkosc wiatru: " + predkoscWiatru + " km/h, \nWidocznosc: " + widocznosc + " km\n";
    }

    public String[][] PogodaTeraz(JsonObject json, String miasto) {
        System.out.println(json.getAsJsonObject());
        String[][] tekst = new String[1][3];
        //tekst[0][0] = GetHeader(json);
        tekst[0][1] = "Miejsce: " + miasto + " " + Math.round(latlon[0] * 10000) / 10000f + " " + Math.round(latlon[1] * 10000) / 10000f;
        tekst[0][2] = GetWeatherDescriptionNow(json);
        return tekst;
    }

    public String[][] PogodaGodzinowo(JsonObject json) {
        //System.out.println(json);
        String[][] tekst = new String[50][3];
        int i = 0;
        for (JsonElement jObj : json.get("data").getAsJsonArray()) {
            //tekst[i][0] = GetHeader(json) + "" + new java.util.Date((long) jObj.getAsJsonObject().get("time").getAsInt() * 1000);
            tekst[i][2] = GetWeatherDescriptionNow(jObj.getAsJsonObject());

            if (jObj.getAsJsonObject() == json.get("data").getAsJsonArray().get(12).getAsJsonObject()) {
                break;
            }
            i++;
        }
        return tekst;
    }

    public String[][] PogodaDziennie(JsonObject json) {
        //System.out.println(json.get("data").getAsJsonArray().get(0).getAsJsonObject());
        String[][] tekst = new String[8][3];
        int i = 0;
        for (JsonElement jObj : json.get("data").getAsJsonArray()) {
            //tekst[i][0] = GetHeader(json) + "" + new java.util.Date((long) jObj.getAsJsonObject().get("time").getAsInt() * 1000);
            tekst[i][2] = GetWeatherDescriptionDaily(jObj.getAsJsonObject());
        }
        return tekst;
    }

    public void Init(URL pogURL, double[] pos) throws ProtocolException, IOException {
        System.out.println(pogURL);
        // _rootJson = PobierzPogodeJSON(pogURL).getAsJsonObject();

        //JsonObject teraz = rootJSON.getAsJsonObject().getAsJsonObject("currently");
        //JsonObject godzinowo = rootJSON.getAsJsonObject().getAsJsonObject("hourly");
        //JsonObject dziennie = ;
        //JsonArray alerty = rootJSON.getAsJsonObject().getAsJsonArray("alerts");
        //DarkSkyWeatherProcessor.PogodaTeraz(teraz);
        //DarkSkyWeatherProcessor.PogodaGodzinowo(godzinowo);
        //DarkSkyWeatherProcessor.PogodaDziennie(dziennie);
        //DarkSkyWeatherProcessor.System.out.println(json);
    }

    @Override
    public WeatherInfo GetWeatherDaily(double lat, double lon) {
        WeatherInfo wi = new WeatherInfo();
        //var Pogoda = PogodaDziennie(_rootJson.getAsJsonObject().getAsJsonObject("daily"));
        return wi;
    }

    @Override
    public WeatherInfo GetWeatherNow(double lat, double lon) {
        URL weatherURL = null;
        try {
            weatherURL = GetWeatherURL(lat, lon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WeatherInfo weatherInfo = new WeatherInfo();
        var jRoot = getRootJson(lat, lon);
        weatherInfo.description = GetWeatherDescriptionNow(jRoot.getAsJsonObject().getAsJsonObject("currently"));
        //weatherInfo.header = GetHeader(jRoot.getAsJsonObject().getAsJsonObject("currently"));

        return weatherInfo;
    }

    @Override
    public WeatherInfo GetWeatherHours(double lat, double lon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JsonObject getRootJson(double lat, double lon) {
        JsonObject jRoot = null;
        try {
            jRoot = PobierzPogodeJSON(GetWeatherURL(lat, lon));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jRoot;
    }

}
