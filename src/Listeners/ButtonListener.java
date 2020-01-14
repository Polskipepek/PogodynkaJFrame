package Listeners;

import misc.Pogodynka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Pogodynka.SetMiasto();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        Pogodynka.SetTeksty();
        Pogodynka.SetGrafika();
    }
}
