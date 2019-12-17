package misc;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import providers.DarkSkyWeatherProvider;
import providers.ILocationProvider;
import providers.IWeatherProvider;
import providers.OpenCageDataProvider;

import javax.swing.*;
import java.awt.*;

/**
 * @author Michal
 */
public class Pogodynka extends Utils {


    static String miasto;
    IWeatherProvider wp;
    ILocationProvider lp;

    public static void main(String[] args) {
        new Pogodynka().Init();

    }

    void Init() {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));

        wp = new DarkSkyWeatherProvider();
        lp = new OpenCageDataProvider();
        //miasto = JOptionPane.showInputDialog("Wpisz miasto lub obszar");
        //var latlon = lp.GetLatLon(miasto);



        //JOptionPane.showMessageDialog(null,wp.GetWeatherNow(latlon[0], latlon[1]).description);
    }

}
