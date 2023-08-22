import java.util.Arrays;

class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)Math.floor(Math.random() * arr.length);
        };

        var start = System.nanoTime();
        bubble(arr);
        System.out.println(System.nanoTime() - start);
        
    }


    static void bubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[ j + 1] < arr[j]) {               
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }   
        }
    }
}