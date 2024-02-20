import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    public static void main(String[] args) {
                
        List<Integer> al = new ArrayList<Integer>();
        int count = 0;
        while(count <= 10_000_000) {
            al.add((int)Math.floor(Math.random() * 10_000_000));
            count++;
        }
        
        var start = System.nanoTime();
        //mergeSort(c);
        quickSort(al, 0);
        System.out.println((System.nanoTime() - start) );
    }

    static List<Integer> quickSort(List<Integer> li,int depth) {
        if(depth >= 100)
            return li;

        if(li.size() <= 1 ) return li;
        
        List<Integer> links = new ArrayList<Integer>();
        List<Integer> rechts = new ArrayList<Integer>();
        
        int pivot = li.remove(0);
        for( Integer i : li) {
            if(i < pivot) {
                links.add(i);
            } else {
                rechts.add(i);
            }
        }

        links = quickSort(links,depth + 1);
        links.add(pivot);
        links.addAll(quickSort(rechts,depth + 1));

        return links;

    }
}
