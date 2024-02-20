package Oberserver;

import javax.swing.*;
import java.awt.*;

public class Anzeige extends JFrame {

    private MyCanvas mainCanvas;

    public Anzeige(Tank tank) {
        tank.subscribe(this);

        setTitle("Füllstand");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisiere die Canvas und füge sie zur JFrame hinzu
        mainCanvas = new MyCanvas();
        add(mainCanvas);

        setVisible(true);
    }    
    
    public void printUpdate(int value, int max) {
        double m = max;
        double v = value;
        double dev = 100;
        mainCanvas.percentage = dev / (m / v);
        mainCanvas.updateUI();
    }

}


class MyCanvas extends JPanel {

    public double percentage;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zeichne ein Rechteck
        g.setColor(Color.WHITE);
        g.fillRect(150, 50, 50, 110);
        
        double numberOfDisPlays =  percentage / 20;
        int y = 140;

        for (int i = 0; i < numberOfDisPlays; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(165, y, 20, 10);
            y = y - 20;
        }

    }

}