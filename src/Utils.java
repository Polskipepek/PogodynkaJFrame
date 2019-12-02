/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Michal
 */
public class Utils {

    public static class Utilities {

        public static final String keyWeather = "53d53eee8101f2e59a7636f1f9469905";
        public static final String keyGeo = "4ded2e1968404981bd1b80b716cdd41e";

        public static String getLanguageCode() {
            return System.getProperty("user.language");
        }


        public static double ConvertDMStoLL(String DMS) {
            //System.out.println(DMS);

            //DMS.replaceAll("^ +| +$|( )+", "$1");
            String array[] = DMS.split(" ");

            int nDegree = Integer.parseInt(array[0]);
            int nMinute = Integer.parseInt(array[1]);
            double nSecond = Double.parseDouble(array[2]);
            double nDegrees = nDegree + (double) nMinute / 60 + (double) nSecond / 3600;
            //String nResult = "" + Double.toString(nDegrees).substring(0, 10);
            //System.out.println(nResult);
            return nDegrees;
        }

    }

}
