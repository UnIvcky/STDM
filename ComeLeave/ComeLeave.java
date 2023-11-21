package ComeLeave;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ComeLeave {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy:HH:mm");

    public LocalDateTime datetime;
    public boolean in;
    public int count;

    public ComeLeave(
        String date,
        String time,
        boolean in,
        int count
    ){
        
        this.in = in;
        this.count = count;
        this.datetime = LocalDateTime.parse(date+":"+time,formatter);

    }

    @Override
    public String toString() {
        return datetime.toString()+ " => " + in + "  " + count + "\n";
    }


    static public int[][]  countVistors(ArrayList<ComeLeave> entries){
        entries.sort((o1, o2) -> o1.datetime.compareTo(o2.datetime));
        int lastHour = entries.get(0).datetime.getHour();
        int lastDay  = entries.get(0).datetime.getDayOfYear();
        
        int maxDay = entries.get(entries.size() - 1).datetime.getDayOfYear();
        int length = maxDay-lastDay;
        int hourSize = 24;
        int[][] ara_ara = new int[365][hourSize];

        int nextBuffer = 0;

        for(var cl: entries){
            int hour = cl.datetime.getHour();
            int day = cl.datetime.getDayOfYear();

            if(hour != lastHour && lastDay == day){
                ara_ara[day][hour] += ara_ara[day][lastHour];
                lastHour = hour;
                ara_ara[day][hour] -= nextBuffer;
                nextBuffer = 0;
            }

            if(day != lastDay){
                lastDay = day;
                lastHour = hour;
                nextBuffer = 0;
            }

            if(cl.in)
                ara_ara[day][hour] += cl.count;
            else 
                nextBuffer += cl.count;

        }
        return ara_ara;
    }

}
