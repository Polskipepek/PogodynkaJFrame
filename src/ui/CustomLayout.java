package ui;

import Listeners.ButtonListener;
import misc.Pogodynka;
import misc.Utils;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.Date;

public class CustomLayout extends JFrame {
    private JButton szukajButton;
    private JLabel timeLbl;
    private JLabel cityLbl;
    private JLabel textWeatherNow;
    private JPanel JPHead;
    private JPanel JPanelWeatherRoot;
    private JPanel JPanelWeatherParent;
    private JTextArea textWeatherDaily;
    private JTextArea textWeatherHourly;
    private JScrollPane scrollHourly;
    private JScrollPane scrollDaily;


    public JLabel getTimeLbl() {
        return timeLbl;
    }

    public JLabel getCityLbl() {
        return cityLbl;
    }

    public JTextArea getTextWeatherDaily() {
        return textWeatherDaily;
    }

    public JTextArea getTextWeatherHourly() {
        return textWeatherHourly;
    }

    public JLabel getTextWeatherNow() {
        return textWeatherNow;
    }

    public JButton getSzukajButton() {
        return szukajButton;
    }



    public void setTimeLbl(Date date) {
        if (Pogodynka.miasto != null)
            //timeLbl.setText(Utils.Utilities.UnixToDate(time).toString());
            timeLbl.setText(date.toString());
    }


    public CustomLayout() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JPanelWeatherRoot = new JPanel();
        JPanelWeatherParent = new JPanel();
        cityLbl = new JLabel();
        timeLbl = new JLabel();
        JPHead = new JPanel();
        textWeatherNow = new JLabel();
        textWeatherHourly = new JTextArea();
        textWeatherDaily = new JTextArea();
        szukajButton = new JButton();
        scrollHourly = new JScrollPane(textWeatherHourly);
        scrollDaily = new JScrollPane(textWeatherDaily);

        setTitle("Pogodynka Michała");

        setLayout(new BorderLayout());

        //add(cityLbl);
        add(JPHead, BorderLayout.PAGE_START);

        JPHead.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

        JPHead.add(cityLbl);
        JPHead.add(timeLbl);
        JPHead.add(szukajButton);

        cityLbl.setText("Obecne miasto");
        cityLbl.setFont(new Font(timeLbl.getFont().getName(), Font.PLAIN, timeLbl.getFont().getSize() + 10));
        timeLbl.setFont(new Font(timeLbl.getFont().getName(), Font.PLAIN, timeLbl.getFont().getSize() + 10));
        szukajButton.setText("Szukaj");


        add(JPanelWeatherRoot, BorderLayout.CENTER);
        JPanelWeatherRoot.setLayout(new BorderLayout());

        JPanelWeatherRoot.add(textWeatherNow, BorderLayout.PAGE_START);
        textWeatherNow.setText("POGODA TERAZ!");
        textWeatherNow.setFont(new Font(textWeatherNow.getFont().getName(), Font.PLAIN, textWeatherNow.getFont().getSize() + 5));
        //JPanelWeatherRoot.add(scrollHourly, BorderLayout.LINE_START);

        JPanelWeatherRoot.add(JPanelWeatherParent, BorderLayout.CENTER);
        JPanelWeatherParent.setLayout(new GridLayout());

        JPanelWeatherParent.add(scrollHourly );
        textWeatherHourly.setFont(new Font(textWeatherHourly.getFont().getName(), Font.PLAIN, textWeatherHourly.getFont().getSize() + 5));
        textWeatherHourly.setText("POGODA GODZINOWO!");


        JPanelWeatherParent.add(scrollDaily);
        textWeatherDaily.setFont(new Font(textWeatherDaily.getFont().getName(), Font.PLAIN, textWeatherDaily.getFont().getSize() + 5));
        textWeatherDaily.setText("POGODA DZIENNIE");

        Utils.WindowSize ws = new Utils.WindowSize();
        ws.width = 1280;
        ws.height = 720;
        setSize(ws.width, ws.height);
        textWeatherNow.setFocusable(false);
        textWeatherHourly.setFocusable(false);
        textWeatherDaily.setFocusable(false);

        szukajButton.addActionListener(new ButtonListener());

    }

}
