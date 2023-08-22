import java.util.Arrays;
import java.util.Random;


public class InsertionSort {
    
    static double start;
    public static void main(String[] args) {
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)Math.floor(Math.random() * arr.length);
        };

        start = System.nanoTime();
        //sort(arr);
        sort2(arr);
        //LucasDreck(arr);
        System.out.println((System.nanoTime() - start) / 1000000000);
    }

    static void sort(int[] arr) {
        
        for (int i = 1; i < arr.length; i++) {
            int abc = arr[i];
            int index = i;            
            while(--index >= 0 && abc  < arr[index] ) {
                   arr[index+1] = arr[index];
            }
            arr[index +1] = abc;
        }
    }

    static void sort2 (int[] arr){
        int sw = arr.length / 4;
        // while(sw < arr.length)
        //     sw *= 2;

        // sw --;
        // sw/=2 ;

        for (; sw > 0; sw /= 2) {
            for (int i = sw; i < arr.length; i++) {
                int abc = arr[i];
                int index = i;            
                while((index -= sw) >= 0 && abc  < arr[index] ) {
                   arr[index+sw] = arr[index];
                }
            arr[index +sw] = abc;
            } 
        }
    }

    static void LucasDreck (int [] arrayToSort) {
        for (int i = 1; i < arrayToSort.length; i++) {
        int index = i;
            while (index != 0 && arrayToSort[index-1] > arrayToSort[index]) {
                int numberToMove = arrayToSort [index];
                arrayToSort [index] = arrayToSort [index-1];
                arrayToSort [index-1] = numberToMove;
                index--;
            }
        }
    }    
}