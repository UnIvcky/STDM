package Oberserver;

import java.util.Scanner;

public class Init {
    public static void main(String[] args) {
        Tank tank = new Tank();
        new Anzeige(tank);

        tank.setFuellstand(600);

        while(true) {
            if(tank.getFuellstand() == 1000) {
                tank.setFuellstand(0);
            } else {
                tank.setFuellstand(tank.getFuellstand() + 100);
            }
            try {
                Thread.sleep(200);
            } catch(Exception e) {}
        }
    }
}