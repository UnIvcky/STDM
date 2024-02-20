package ComeLeave;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static ArrayList<String[]> read(String seperator,String filepath) throws FileNotFoundException {

        ArrayList<String[]> parts = new ArrayList<String[]>();
        
        Scanner sc = new Scanner(new File(filepath));
        while(sc.hasNextLine())
            parts.add(sc.nextLine().split(seperator));

        return parts;
    }

    public static void main(String[] args) throws Exception {

        ArrayList<ComeLeave> list = new ArrayList<>();
        var arrray = read(";", "C:\\testDaten2023Mai.csv");

        for (int i = 1; i < arrray.size(); i++) {
            var s = arrray.get(i);
            list.add(new ComeLeave(    
                s[0],s[1],s[2].equals("0"),Integer.parseInt(s[3])
            ));
        }
        var b = ComeLeave.countVistors(list);

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if(b[i][j] != 0)
                System.out.println( i + ":" + j + " = " + b[i][j] );            }
        }
    }

}
