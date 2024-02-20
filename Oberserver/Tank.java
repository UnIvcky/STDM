package Oberserver;

import java.util.ArrayList;

public class Tank {
    
    private static ArrayList<Anzeige> Observers = new ArrayList<Anzeige>();

    private static int fuellstand;
    private static int max = 1000;

    public void subscribe(Anzeige subscriber) {
        Observers.add(subscriber);        
    }

    private static void sendUpdate(Anzeige anzeige, int fuellstand) {
        anzeige.printUpdate(fuellstand, max);
    }

    public static void setFuellstand(int f){
        fuellstand = f;
        for(Anzeige i : Observers) {
            sendUpdate(i, fuellstand);
        }
    }
    public static int getFuellstand() {
        return fuellstand;
    }

}