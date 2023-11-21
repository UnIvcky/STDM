package ComeLeave.Aktivitätsdiagramm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CAuswertung {
    
    public static void main(String[] args) {
        
        Double[] werte;
        
        Scanner scanner = new Scanner(System.in);
        String inputWerte = scanner.nextLine();

        String[] arrayWerte = inputWerte.split(",");
    
        werte = new Double[arrayWerte.length];

        int count = 0;
        for(String wert: arrayWerte) {
            werte[count] = Double.parseDouble(wert);
            count++;    
        }

        System.out.println(Arrays.toString(werte));
        System.out.println("What operation would you like?");
        scanner = new Scanner(System.in);

        
        try {
            int operation = Integer.parseInt(scanner.nextLine());
            switch (operation) {
                case 1: // minimum
                    Double min = getMinimum(werte);
                    System.out.println();                    
                    break;
            
                default:
                    break;
            }
        } catch(Exception e) {}
    }

    private static Double getMinimum(Double[] werte) {
        Double min = Arrays.sort(werte)[0]; 
        return min;
    }
        
}
