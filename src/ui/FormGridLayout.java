package ui;

import misc.Utils;

import javax.swing.*;

public class FormGridLayout extends JFrame {
    private FormGridLayout formGridLayout;
    public JPanel RootJPanel;
    public JLabel lblTime;
    public JLabel lblCity;
    public JPanel JPHead;
    public JPanel JPanelWeatherHours;
    public JPanel JPanelWeatherDaily;
    public JTextArea textWeatherDaily;
    public JTextArea textWeatherHourly;
    public JLabel textWeatherNow;
    private JPanel JPanelWeatherRoot;
    private JButton szukajButton;
    public JScrollPane scrollHourly;

    public FormGridLayout() {
        add(RootJPanel);
        setTitle("To jest tytuł");
        Utils.WindowSize ws = new Utils.WindowSize();
        ws.width = 1280;
        ws.height = 720;
        setSize(ws.width, ws.height);
        textWeatherNow.setFocusable(false);
        textWeatherHourly.setFocusable(false);
        textWeatherDaily.setFocusable(false);
       // scrollHourly = new JScrollPane(textWeatherHourly,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

    private void createUIComponents() {
        //imageH = new JLabel(new ImageIcon(""));
    }
}
