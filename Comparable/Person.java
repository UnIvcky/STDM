package Comparable;

public class Person implements Comparable<Person>{
    String Name;
    String Vorname;
    char Middle_Initial;
    int Groeße_cm;
    int Gewicht_kg;
    double bmi;
    
    public Person(String Name, String Vorname, char Middle,  int Groeße_cm, int Gewicht_kg) {
        this.Name = Name;
        this.Vorname = Vorname;
        this.Middle_Initial = Middle;
        this.Groeße_cm = Groeße_cm;
        this.Gewicht_kg = Gewicht_kg;
        this.bmi = Gewicht_kg / (Groeße_cm * Groeße_cm) ;
    }

    @Override
    public int compareTo(Person other) {
        
        int diffrence_kg = this.Gewicht_kg - other.Gewicht_kg;
        if(diffrence_kg != 0 )
            return diffrence_kg;

        int diffrence_bmi = this.bmi - other.bmi;
        if(diffrence_bmi != 0)
            return diffrence_bmi;

        int diffrence_name = this.Name.compareTo(other.Name);
        if(diffrence_name != 0)
            return diffrence_name;


        return  this.Vorname.compareTo(other.Vorname);
    }

    public String toString() {
        return this.Vorname+" "+this.Name+" " + this.Gewicht_kg;
    }
    

}