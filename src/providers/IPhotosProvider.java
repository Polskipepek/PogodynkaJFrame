package providers;

import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public interface IPhotosProvider {
    URL GetURLRoot() throws MalformedURLException;
    URL GetURLFull(String s) throws  MalformedURLException;
    ImageIcon GetPhoto(String s) throws MalformedURLException;
    JsonObject GetJSON(URL url) throws IOException;
    Image GetRandomPhotoFromJSON(JsonObject j);

}
