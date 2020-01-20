package misc;

import com.google.gson.JsonObject;
import providers.*;
import ui.CustomLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLSyntaxErrorException;

/**
 * @author Michal
 */
public class Pogodynka extends Utils {
    public static String miasto;
    public static IWeatherProvider wp;
    public static ILocationProvider lp;
    public static IPhotosProvider pp;
    static CustomLayout layout = null;
    static JsonObject rootJson = null;
    public static JFrame GrafikaHourly = null, GrafikaDaily = null;


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        new Pogodynka().Init();
    }

    void Init() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
        wp = new DarkSkyWeatherProvider();
        lp = new OpenCageDataProvider();
        pp = new UnsplashPhotosProvider();

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
        var tempMiasto = JOptionPane.showInputDialog("Wpisz miasto, aby sprawdzić pogodę");
        while (tempMiasto == null || tempMiasto.contains(" ") || tempMiasto.contains("-")) {
            if (tempMiasto == null || tempMiasto.contains(" ") || tempMiasto.contains("-")) {
                miasto = JOptionPane.showInputDialog("Wpisz poprawna nazwe miasta, aby sprawdzić pogodę");
                if (miasto == null || miasto.contains(" "))
                    miasto = JOptionPane.showInputDialog("Ostatnia szansa, aby sprawdzić pogodę");
                else System.exit(0);
            }
        }
        miasto = tempMiasto;
        var latlon = lp.GetLatLon(miasto);
        rootJson = wp.getRootJson(latlon[0], latlon[1]);


    }

    public static void SetTeksty() {
        layout.getCityLbl().setText(miasto);
        layout.getCityLbl().setText(miasto + ": Strefa czasowa " + rootJson.get("timezone").getAsString());
        layout.setTimeLbl(Utilities.UnixToDate(Integer.parseInt(wp.GetHeader(rootJson, "time"))));
        layout.getTextWeatherNow().setText(wp.GetWeatherNow(rootJson).description);
        layout.getTextWeatherHourly().setText(wp.GetWeatherHourly(rootJson).description);
        layout.getTextWeatherDaily().setText(wp.GetWeatherDaily(rootJson).description);
    }

    public static void SetGrafika() throws IOException {
        //ImageIcon hourlyBackground = new ImageIcon("Graphics/" + wp.GetWeatherHourly(rootJson).imageName);
        //ImageIcon dailyBackground = new ImageIcon("Graphics/" + wp.GetWeatherDaily(rootJson).imageName);


        String nazwaObrazkaH = wp.GetWeatherHourly(rootJson).imageName;
        String nazwaObrazkaD = wp.GetWeatherHourly(rootJson).imageName;
        JsonObject jsonPPh = pp.GetJSON(pp.GetURLFull(nazwaObrazkaH));
        JsonObject jsonPPd = pp.GetJSON(pp.GetURLFull(nazwaObrazkaD));

        Image himage = pp.GetRandomPhotoFromJSON(jsonPPh);
        ImageIcon hourlyBackground = new ImageIcon(himage);

        Image dimage = pp.GetRandomPhotoFromJSON(jsonPPd);
        ImageIcon dailyBackground = new ImageIcon(dimage);


        pp.GetURLFull(wp.GetWeatherDaily(rootJson).imageName);


        GrafikaHourly = new JFrame("Godzinowo " + nazwaObrazkaH);
        GrafikaHourly.setSize(500, 500);
        JLabel labelH = new JLabel("", hourlyBackground, JLabel.CENTER);
        labelH.setBounds(0, 0, 500, 500);
        GrafikaHourly.add((labelH));
        GrafikaHourly.setVisible(true);
        GrafikaHourly.revalidate();
        GrafikaHourly.pack();


        GrafikaDaily = new JFrame("Dziennie " + nazwaObrazkaD);
        GrafikaDaily.setSize(500, 500);
        JLabel labelD = new JLabel("", dailyBackground, JLabel.CENTER);
        labelD.setBounds(0, 0, 500, 500);
        GrafikaDaily.add(labelD);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - 2 * GrafikaDaily.getWidth();
        int y = 0;
        GrafikaDaily.setLocation(x, y);

        GrafikaDaily.setVisible(true);
        GrafikaDaily.revalidate();
        GrafikaDaily.pack();


    }


}
