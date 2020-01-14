package providers;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public interface IPhotosProvider {
    URL GetURLRoot() throws MalformedURLException;
    URL GetURLFull(String s) throws  MalformedURLException;
    ImageIcon GetPhoto(String s) throws MalformedURLException;
    ImageIcon GetRandomPhoto();

}
