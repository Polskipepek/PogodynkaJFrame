/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.awt.Font;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * @author Michal
 */
public class Pogodynka extends Utils {

   /* public static String miasto;
    public static double[] latlon;
    public static JsonObject rootJSON = null;*/

    static String miasto;
    IWeatherProvider wp;
    ILocationProvider lp;

    public static void main(String[] args) {
        new Pogodynka().Init();
        /*URL posURL = OpenCageDataProvider.GetPositionURL(miasto);
        latlon = OpenCageDataProvider.Pozycja(posURL, miasto);
        URL weatherURL = DarkSkyWeatherProvider.GetWeatherURL(latlon[0], latlon[1]);
        Pogoda(weatherURL, latlon);

        System.out.println(miasto + "\t" + latlon[0] + " N\t" + latlon[1] + " E\n");

        //InitJFrame.Init();*/
    }

    void Init() {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
        wp = new DarkSkyWeatherProvider();
        lp = new OpenCageDataProvider();
        miasto = JOptionPane.showInputDialog("Wpisz miasto lub obszar");
        var latlon = lp.GetLatLon(miasto);

        //InitJFrame.Init();

        JOptionPane.showMessageDialog(null,wp.GetWeatherNow(latlon[0], latlon[1]).description);
    }

//
}
