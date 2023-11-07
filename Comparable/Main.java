package Comparable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class Main {

    static String[] names = { "Wortmann", "Vogt", "Ferdinant", "Von der Heiden", "Telmann", "Schulz", "Goethe", "da Vinci", "Watson", "Holmes" };
    static String[] vornamen = { "Marcel", "Joshua", "Lukas", "Luca", "Max", "Laxmi", "Johann", "Leonardo", "Doktor", "Sherlock" };

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        List<Person> persons = new ArrayList<Person>();

        Random random = new Random(123);

        for (int i = 0; i < 10; i++) {
            
            int groeße = 0;
            int gewicht = 0;

            do {
                groeße = random.nextInt(150, 200);
                gewicht = random.nextInt(50, 90);
            } while ((groeße / gewicht * gewicht) <= 45 && (groeße / gewicht * gewicht) >= 16  );
            
            persons.add(new Person( 
                names[(int)Math.floor(Math.random() * names.length)], 
                vornamen[(int)Math.floor(Math.random() * vornamen.length)],
                (char)(random.nextInt(0, 25) + 'a'),
                groeße,
                gewicht
            ));
        }

        
        Comparator<Person> gewichtComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.Gewicht_kg - o2.Gewicht_kg;
            }
        };
        Comparator<Person> groesseComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.Groeße_cm - o2.Groeße_cm;
            }
        };
        Comparator<Person> nameComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.Name.compareTo(o2.Name);
            }
        };
        Comparator<Person> VornameComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.Vorname.compareTo(o2.Vorname);
            }
        };
        Comparator<Person> bmiComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.bmi - o2.bmi;
            }
        };

        Comparator<Person> irgendeinComparator = (p1,p2)->{return p1.compareTo(p2);};

        persons.sort(Comparator.comparing((Person p)->p.Name).thenComparing((Person p)->p.Vorname));
        
        System.out.println(persons);

        //var t = _quickSort(persons,(a,b)->{
            //return true;
        //});
    }

/* 
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
*/

}
