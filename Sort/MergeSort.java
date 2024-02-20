import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        int[] arrA = {1,3,5,7,9,11};
        int[] arrB = {2,4,6,8,10,12,14};

        int[] c = new int[100];
        for (int i = 0; i < c.length; i++) {
            c[i] = (int)Math.floor(Math.random() * c.length);
        };
                
        List<Integer> al = new ArrayList<Integer>();
        int count = 0;
        while(count <= 100_000_000) {
            al.add((int)Math.floor(Math.random() * 100_000_000));
            count++;
        }
        
        var start = System.nanoTime();
        //mergeSort(c);
        System.out.println(quickSort(al));
        System.out.println((System.nanoTime() - start) );
    }
         
    static int[] merge(int[] a, int[] b){

        int[] c =  new int[a.length + b.length];     

        int a_pos = 0;
        int b_pos = 0;

        for (int i = 0; i < c.length; i++) {
            if(b_pos >= b.length || a_pos < a.length && a[a_pos] < b[b_pos]){
                c[i] = a[a_pos];
                a_pos++;
            }else if(b_pos < b.length){
                c[i] =  b[b_pos];
                b_pos++;
            }    
        
        }
        return c;
    }

    static int[] mergeSort(int[] ara){
        if(ara.length <= 1) return ara;

        int [] rechts, links;

        links = Arrays.copyOfRange(ara, 0, ara.length/2);
        rechts  = Arrays.copyOfRange(ara, ara.length/2, ara.length);

        links  = mergeSort(links);
        rechts = mergeSort(rechts);
        
        return merge(links, rechts);
    }

    static List<Integer> quickSort(List<Integer> li){
        return quickSort(li, 0);
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


    public static <T> List<T> _quickSort(List<T> li,Closure<T> closure) {

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