import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Personalausweisnummer {
    private String serial;

    private LocalDate birth_date;
    private LocalDate experition_date;
    private int line_1;

    static final char fill = '<';
    final int[] multiplierArr = {7,3,1};

    private String nachname;
    private String vorname;

    public Personalausweisnummer(
            String line_1,
            String line_2,
            String line_3
    ){
        this.construct(line_1,line_2,line_3);
    }


    private void checkChecksum(String line1,String line2, String line3) {

        System.out.println("Checking Checksum... ");
        char cn1 = line1.toCharArray()[14];
        char cn2 = line2.toCharArray()[6];
        char cn3 = line2.toCharArray()[14];
        
        char[] checknums = {cn1,cn2,cn3};

        char[] cs1 = line1.substring(5,14).toCharArray();
        char[] cs2 = line2.substring(0,6).toCharArray();
        char[] cs3 = line2.substring(8,14).toCharArray();

        char[][] checkStrings = {cs1,cs2,cs3};
        int super_sum = 0;
        ArrayList<Character> super_arr = new ArrayList<Character>();

        for (int i = 0; i < 3 ; i++) {
            char[] cs = checkStrings[i];
            char cn = checknums[i];

            if(!(cn >= '0' && '9' >= cn))
                throw  new PersoCharacterException();

            int sum = 0;

            for (int j = 0 ; j < cs.length; j++) {
                char csc = cs[j];
                int mul = multiplierArr[j%3];
                int tmp;
                if('A' <= csc && csc <= 'Z')
                    tmp = (csc -'A' + 10) * mul;
                else if ('0' <= csc && csc <= '9')
                    tmp = (csc - '0') * mul;
                else
                    throw new PersoCharacterException();
                sum += tmp;
                super_arr.add(csc);
            }
            super_arr.add(cn);


            if(! (sum % 10 == (cn-'0')))
                throw new PersoChecksumException();

        }


        for (int i = 0; i < super_arr.size(); i++) {
            char csc = super_arr.get(i);
            int mul = multiplierArr[i%3];
            int tmp;
            if('A' <= csc && csc <= 'Z')
                tmp = (csc -'A' + 10) * mul;
            else if ('0' <= csc && csc <= '9')
                tmp = (csc - '0') * mul;
            else
                throw new PersoCharacterException();
            super_sum += tmp;
        }

        if(super_sum% 10 != line2.charAt(29)-'0')
            throw new PersoChecksumException();

        System.out.println("Checksum passed!");
    }

        private void checkFormat(String line1,String line2, String line3) {

        System.out.println("Checking Format.. ");

        if (
            !line1.substring(0, 3).equals("IDD") ||
            !line1.substring(3,5).equals("<<") ||
            !line1.substring(15, 29).equals("<<<<<<<<<<<<<<")
        ) { throw new PersoFormatException(); }

        if(
            !(line1.substring(6,14).indexOf("A") == -1) ||
            !(line1.substring(6,14).indexOf("E") == -1) ||
            !(line1.substring(6,14).indexOf("I") == -1) ||
            !(line1.substring(6,14).indexOf("O") == -1) ||
            !(line1.substring(6,14).indexOf("U") == -1)
        ) {
            throw new PersoCharacterException();
        }

        if(
            line1.length() != 30 ||
            line2.length() != 30 ||
            line3.length() != 30
        ) { throw new PersoFormatException();}

        if(
            !line2.substring(7, 8).equals("<") ||
            line2.charAt(15) != 'D' ||
            !line1.substring(16,29).equals("<<<<<<<<<<<<<")
        ) {throw new PersoFormatException(); }

        if(!Pattern.matches("[0-9]", line2.substring(29,30))) {
            throw new PersoCharacterException();
        }

        if(
            Pattern.matches("[0-9]" , line3) ||
            !line3.substring(line3.indexOf('<'), line3.indexOf('<') + 2).equals("<<")
        ) { throw new PersoFormatException(); }


        if(!(Pattern.matches("[0-9]+", line2.substring(0,6)))) {
            throw new PersoCharacterException();
        }
        System.out.println("Format passed!");

    }


    public Personalausweisnummer(
           String[] lines
    ){
        if(lines.length != 3) throw new PersoFormatException();
        this.construct(lines[0],lines[1],lines[2]);

    }

    public static void main(String[] args) {
        String line1 = "IDD<<T220001293<<<<<<<<<<<<<<<";
        String line2 = "0208127<2610313D<<<<<<<<<<<<<0";
        String line3 = "SCHMIDT<<KEVIN<<<<<<<<<<<<<<<<";
        Personalausweisnummer p = new Personalausweisnummer(line1, line2, line3);

    }

    public LocalDate getAblDat() {
        return this.experition_date;
    }

    public LocalDate getGebDat() {
        return this.birth_date;

    }



    public void construct(
            String line_1,
            String line_2,
            String line_3
    ){
        checkFormat(line_1,line_2,line_3);
        checkChecksum(line_1,line_2,line_3);
        parseL1(line_1);
        parseL2(line_2);
        parseL3(line_3);
    }

    public void parseL3(String line){
        int fill_pos = line.indexOf(fill);
        this.nachname = line.substring(0,fill_pos-1);
        String tmp = line.substring(fill_pos+1);
        fill_pos = tmp.indexOf(fill);
        this.vorname = tmp.substring(0,fill_pos);
    }

    public void parseL2(String line){

        String year = "";
        String month;
        String day;

        if(Integer.parseInt(line.substring(0,2)) < 23 && Integer.parseInt(line.substring(0,2)) >= 0){
            year = "20" + line.substring(0,2);
        } else  {
            year = "19" + line.substring(0,2);
        }

        try {
            this.birth_date = LocalDate.parse(year + "-" + line.substring(2,4) + "-" + line.substring(4,6));
            this.experition_date = LocalDate.parse("20" +  line.substring(8,10) + "-" + line.substring(10,12) + "-" + line.substring(12,14));
        } catch(Exception e) {
            throw new PersoDateException();
        }

    }

    public void  parseL1(String line){
        this.serial = line.substring(5,14);
    }


    public String getSerial(){
        return this.serial;
    }


}
