import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
 
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)Math.floor(Math.random() * arr.length);
        };

        System.out.println( Arrays.toString(selection(arr)));
  
    }


    static int[] bubble(int[] arr) {
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    static int[] insertion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while(j > -1 && current < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
        return arr;
    }

    static int[] selection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i; 
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[min]) min = j;
            }
            if(min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }
}