package providers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class UnsplashPhotosProvider implements IPhotosProvider {

    private String accessKey = "4cf9dfec0c82b5260cd10739db817fedaa5081057146a9cedb6a7de74d154982";
    private String secretKey = "843c09d8b5d799d64575a9fb109eb69c8a5a56fab359ae3c76b4c9b28439a66f";
    Random random = new Random();

    public URL GetURLRoot() throws MalformedURLException {
        return new URL("https://api.unsplash.com/search?query=");

    }

    String URLWithKey() {
        return "&client_id=" + accessKey;
    }

    @Override
    public URL GetURLFull(String s) throws MalformedURLException {
        URL url = new URL(GetURLRoot() + s + URLWithKey() + "&per_page=25");
        System.out.println(url);
        return url;
    }

    @Override
    public ImageIcon GetPhoto(String s) throws MalformedURLException {
        System.out.println(GetURLFull(s));

        return null;
    }

    @Override
    public JsonObject GetJSON(URL url) throws IOException {
        URLConnection request = url.openConnection();
        request.connect();
        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootObj = root.getAsJsonObject();

        System.out.println("Pobierz PP JSON: " + rootObj);
        return rootObj;
    }


    @Override
    public Image GetRandomPhotoFromJSON(JsonObject json) {
        int number = random.nextInt(24);
        String url = json.get("photos").getAsJsonObject().get("results").getAsJsonArray().get(number).getAsJsonObject().get("urls").getAsJsonObject().get("regular").getAsString();
        Image image = null;
        try {
            image = ImageIO.read(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
