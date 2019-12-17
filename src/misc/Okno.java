package misc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Okno extends JFrame {
    JFrame frame;

    public Okno(int h, int w){
        frame = new JFrame();
        frame.setSize(w,h);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

