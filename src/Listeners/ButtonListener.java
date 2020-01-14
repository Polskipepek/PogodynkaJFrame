package Listeners;

import misc.Pogodynka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

public class ButtonListener implements ActionListener  {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Pogodynka.SetMiasto();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        Pogodynka.SetTeksty();
        Pogodynka.GrafikaDaily.dispatchEvent(new WindowEvent(Pogodynka.GrafikaDaily,WindowEvent.WINDOW_CLOSING));
        Pogodynka.GrafikaHourly.dispatchEvent(new WindowEvent(Pogodynka.GrafikaHourly,WindowEvent.WINDOW_CLOSING));
        try {
            Pogodynka.SetGrafika();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}
