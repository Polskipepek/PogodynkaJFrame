package providers;

import java.net.MalformedURLException;

public interface ILocationProvider {
    double[] GetLatLon(String miasto) throws MalformedURLException;
}
