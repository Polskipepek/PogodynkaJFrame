package misc;

import com.google.gson.JsonObject;
import providers.DarkSkyWeatherProvider;
import providers.ILocationProvider;
import providers.IWeatherProvider;
import providers.OpenCageDataProvider;
import ui.CustomLayout;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

/**
 * @author Michal
 */
public class Pogodynka extends Utils {
    public static String miasto;
    public static IWeatherProvider wp;
    public static ILocationProvider lp;
    static CustomLayout layout = null;
    static JsonObject rootJson = null;


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, MalformedURLException {
        new Pogodynka().Init();
    }

    void Init() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, MalformedURLException {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
        wp = new DarkSkyWeatherProvider();
        lp = new OpenCageDataProvider();

        Utils.WindowSize windowSize = new WindowSize();
        windowSize.height = 720;
        windowSize.width = 1280;

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

 /*       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                layout = new CustomLayout();
                layout.setSize(windowSize.width, windowSize.height);
                layout.setLocationRelativeTo(null);
                layout.setVisible(true);
                layout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });*/

        SwingUtilities.invokeLater(() -> {
            layout = new CustomLayout();
            layout.setSize(windowSize.width, windowSize.height);
            layout.setLocationRelativeTo(null);
            layout.setVisible(true);
            layout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });

        SetMiasto();
        //System.out.println(wp.GetWeatherURL(latlon[0], latlon[1]));
        SetTeksty();
        SetGrafika();

    }

    public static void SetMiasto() throws MalformedURLException {
        miasto = JOptionPane.showInputDialog("Wpisz miasto, aby sprawdzić pogodę");
        if (miasto.contains(" ") || miasto == null) {
            miasto = JOptionPane.showInputDialog("Wpisz poprawna nazwe miasta, aby sprawdzić pogodę");
            if (miasto.contains(" ") || miasto == null)
                miasto = JOptionPane.showInputDialog("Ostatnia szansa, aby sprawdzić pogodę");
            else System.exit(0);
        }
        var latlon = lp.GetLatLon(miasto);
        rootJson = wp.getRootJson(lp.GetLatLon(miasto)[0], lp.GetLatLon(miasto)[1]);

    }

    public static void SetTeksty() {
        layout.getCityLbl().setText(miasto);
        layout.getCityLbl().setText(miasto + ": Strefa czasowa " + rootJson.get("timezone").getAsString());
        layout.setTimeLbl(Utilities.UnixToDate(Integer.parseInt(wp.GetHeader(rootJson, "time"))));
        layout.getTextWeatherNow().setText(wp.GetWeatherNow(rootJson).description);
        layout.getTextWeatherHourly().setText(wp.GetWeatherHourly(rootJson).description);
        layout.getTextWeatherDaily().setText(wp.GetWeatherDaily(rootJson).description);
    }

    public static void SetGrafika() {
        ImageIcon hourlyBackground = new ImageIcon("Graphics/" + wp.GetWeatherHourly(rootJson).imageName);
        ImageIcon dailyBackground = new ImageIcon("Graphics/" + wp.GetWeatherDaily(rootJson).imageName);

        System.out.println(hourlyBackground);

        JFrame GrafikaHourly = new JFrame();
        GrafikaHourly.setSize(500, 500);
        JLabel labelH = new JLabel("", hourlyBackground, JLabel.CENTER);
        labelH.setBounds(0, 0, 500, 500);
        GrafikaHourly.add((labelH));
        GrafikaHourly.setVisible(true);
        GrafikaHourly.pack();


        JFrame GrafikaDaily = new JFrame();
        GrafikaDaily.setSize(500, 500);
        JLabel labelD = new JLabel("", dailyBackground, JLabel.CENTER);
        GrafikaDaily.pack();
        labelD.setBounds(0, 0, 500, 500);
        GrafikaDaily.add(labelD);
        GrafikaDaily.setVisible(true);

    }


}
