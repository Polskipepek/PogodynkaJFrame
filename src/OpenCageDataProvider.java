/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michal
 */
public class OpenCageDataProvider implements ILocationProvider{

    public static URL GetPositionURL(String cityName) {
        try {
            return new URL("https://api.opencagedata.com/geocode/v1/json?q=" + cityName + "&key=" + Utils.Utilities.keyGeo + "&pretty=1");
            //System.out.println(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

        @Override
        public double[] GetLatLon(String miasto) {
        URL posURL = GetPositionURL(miasto);
        System.out.println(posURL.toString());
            JsonElement[] jelements = null;
        try {
            jelements = PobierzPozycjeJSON(posURL);
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
        double lat = Utils.Utilities.ConvertDMStoLL(jelements[0].getAsString().replace("°", "").replace("'", ""));
        double lon = Utils.Utilities.ConvertDMStoLL(jelements[1].getAsString().replace("°", "").replace("'", ""));
        //System.out.println(lat + "\t" + lon);
        //System.out.println(Utils.Utilities.GetWeatherURL(lat, lon));
        return new double[]{lat, lon};
    }
        
    public static JsonElement[] PobierzPozycjeJSON(URL url) throws MalformedURLException, ProtocolException, IOException {
        // Connect to the URL using java's native library
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobjs = root.getAsJsonObject();
        JsonObject results = rootobjs.get("results").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject("annotations").getAsJsonObject("DMS");

        //System.out.println("PobierzJSON: " + results.get("lat"));
        return new JsonElement[]{results.get("lat"), results.get("lng")};
    }


}
