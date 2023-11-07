package Knobel;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Comparable.Closure;

public class Pseudozufallszahlen {
    static int S_zero = 290_797; 
    static int mod = 50_515_093;

    public static void main(String[] args) {
        calcRandom();
    }

    public static int calcRandom() {
        List<Integer> randomNumbers = new ArrayList<Integer>();
        long last = S_zero;

        randomNumbers.add((int)last);
        for (int i = 0; i < 20; i++) {
            int rnd = (int)((last * last) % mod);
            randomNumbers.add( rnd );
            last = rnd;
        }

        constructPoints(randomNumbers);
        return 0;
    }

    public static void constructPoints(List randomNumbers) {

        List<Point> points = new ArrayList<Point>();
 
        for (int i = 0; i < randomNumbers.size() - 1; i+=2) {
            Point p = new Point((int)randomNumbers.get(i),(int)randomNumbers.get(i + 1) );
            points.add(p);
        }

        //System.out.println(Point.getSmallestDistanceBruteforce(points).distance );       

        Point.getSmallestDistanceSweep(points);
        

        
    }


    static <T> List<T> _quickSort(List<T> li,Closure<T> closure) {

        if(li.size() <= 1 ) return li;
        
        List<T> links = new ArrayList<T>();
        List<T> rechts = new ArrayList<T>();
        
        T pivot = li.remove(0);
        for( T i : li) {
            if(closure.run(i, pivot)) {
                links.add(i);
            } else {
                rechts.add(i);
            }
        }

        links = _quickSort(links,closure);
        links.add(pivot);
        links.addAll(_quickSort(rechts,closure));

        return links;

    }


}
