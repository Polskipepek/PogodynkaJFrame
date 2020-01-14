package ui;

import misc.Utils;

import javax.swing.*;

public class Okno extends JFrame {
    private Okno okno;
    private JPanel rootPanel;
    private JPanel HeadPanel;
    private JLabel nowlbl;
    private JScrollPane hourlyLbl;
    private JScrollPane dailyLbl;
    private JTextPane hourlyTP;
    private JTextPane dailyTP;
    private JLabel hourlyImageLabel;
    private JLabel dailyImageLabel;
    private JButton searchBtn;
    private JLabel cityLbl;
    private JLabel hourLbl;

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public void setSearchBtn(JButton searchBtn) {
        this.searchBtn = searchBtn;
    }

    public JLabel getCityLbl() {
        return cityLbl;
    }

    public void setCityLbl(JLabel cityLbl) {
        this.cityLbl = cityLbl;
    }

    public JLabel getHourLbl() {
        return hourLbl;
    }

    public void setHourLbl(JLabel hourLbl) {
        this.hourLbl = hourLbl;
    }

    public JLabel getNowlbl() {
        return nowlbl;
    }

    public void setNowlbl(JLabel nowlbl) {
        this.nowlbl = nowlbl;
    }

    public JScrollPane getHourlyLbl() {
        return hourlyLbl;
    }

    public void setHourlyLbl(JScrollPane hourlyLbl) {
        this.hourlyLbl = hourlyLbl;
    }

    public JScrollPane getDailyLbl() {
        return dailyLbl;
    }

    public void setDailyLbl(JScrollPane dailyLbl) {
        this.dailyLbl = dailyLbl;
    }

    public JTextPane getHourlyTP() {
        return hourlyTP;
    }

    public void setHourlyTP(JTextPane hourlyTP) {
        this.hourlyTP = hourlyTP;
    }

    public JTextPane getDailyTP() {
        return dailyTP;
    }

    public void setDailyTP(JTextPane dailyTP) {
        this.dailyTP = dailyTP;
    }

    public JLabel getHourlyImageLabel() {
        return hourlyImageLabel;
    }

    public void setHourlyImageLabel(JLabel hourlyImageLabel) {
        this.hourlyImageLabel = hourlyImageLabel;
    }

    public JLabel getDailyImageLabel() {
        return dailyImageLabel;
    }

    public void setDailyImageLabel(JLabel dailyImageLabel) {
        this.dailyImageLabel = dailyImageLabel;
    }

    public Okno() {
        okno = new Okno();
        add(rootPanel);
        setTitle("To jest tytuł");
        Utils.WindowSize ws = new Utils.WindowSize();
        ws.width = 1280;
        ws.height = 720;
        setSize(ws.width, ws.height);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
