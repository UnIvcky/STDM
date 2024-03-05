import java.io.InputStream;
import java.util.Scanner;

class Main{

    public static void main(String[] args) {
        InputStream in = System.in;


         Scanner sc = new Scanner(
                         new CeaserChiffre(
                                 in
                         ,3)
         );

        // // new NonAlphaFilter(
        // //     new UpperCaseTransform(in)
        // // )
        //Scanner sc = new Scanner(new VigenereChiffre(in,"ABC"));

        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
    }


}