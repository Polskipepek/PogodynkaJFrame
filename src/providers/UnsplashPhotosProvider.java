package providers;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class UnsplashPhotosProvider implements IPhotosProvider {

    private String accessKey = "4cf9dfec0c82b5260cd10739db817fedaa5081057146a9cedb6a7de74d154982";
    private String secretKey = "843c09d8b5d799d64575a9fb109eb69c8a5a56fab359ae3c76b4c9b28439a66f";

    public URL GetURLRoot() throws MalformedURLException {
        URL url = new URL("https://api.unsplash.com/photos/?client_id=" + accessKey);
        System.out.println(url);
        return url;
    }

    @Override
    public URL GetURLFull(String s) throws MalformedURLException {
        return new URL(GetURLRoot() + "&query=" + s);

    }

    @Override
    public ImageIcon GetPhoto(String s) throws MalformedURLException {
        System.out.println(GetURLFull(s));

        return null;
    }


    @Override
    public ImageIcon GetRandomPhoto() {
        return null;
    }
}
