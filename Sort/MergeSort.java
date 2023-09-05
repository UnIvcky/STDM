import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeSort {
    public static void main(String[] args) {
        int[] arrA = {1,3,5,7,9,11};
        int[] arrB = {2,4,6,8,10,12,14};
        
        //merge(arrA, arrB);
        
        List<Integer> al = new ArrayList<Integer>();
        int count = 0;
        while(count <= 100_000_00) {
            al.add((int)Math.floor(Math.random() * 100_000_00));
            count++;
        }


        int[] c = new int[100_000_00];
        for (int i = 0; i < c.length; i++) {
            c[i] = (int)Math.floor(Math.random() * c.length);
        };

        var start = System.nanoTime();
        mergeSort(c);
        //quickSort(al);
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

    public static int[] mergeSort(int[] ara){
        if(ara.length <= 1) return ara;

        int [] rechts, links;

        links = Arrays.copyOfRange(ara, 0, ara.length/2);
        rechts  = Arrays.copyOfRange(ara, ara.length/2, ara.length);

        links  = mergeSort(links);
        rechts = mergeSort(rechts);
        
        return merge(links, rechts);
    }

    public static List<Integer> quickSort(List<Integer> li) {

        if(li.size() <= 1 ) return li;
        
        List<Integer> links = new ArrayList<Integer>();
        List<Integer> rechts = new ArrayList<Integer>();
        
        Integer pivot = li.remove(0);
        for( Integer i : li) {
            if(i < pivot) {
                links.add(i);
            } else {
                rechts.add(i);
            }
        }

        links = quickSort(links);
        links.add(pivot);
        links.addAll(quickSort(rechts));

        return links;

    }
    
}