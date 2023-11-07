package Knobel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Point {
    
    @Override
    public String toString(){
        return this.x + "  " + this.y;
    }

    public static class Distance{
        public Point a = null;
        public Point b = null;
        public double distance = Double.MAX_VALUE;
    }

    long x;
    long y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double calcDistance(Point a ,Point b){
        return Math.sqrt(
            Math.pow((b.x - a.x),2) + Math.pow((b.y - a.y),2) 
        );
    }

    public static Distance getSmallestDistanceBruteforce(List<Point> points){
        Distance distance = new Distance();
        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                if(i == j) continue;
                double d = Point.calcDistance(points.get(i), points.get(j));
                if(distance.distance > d){
                    distance.distance = d ;
                    distance.a = points.get(i);
                    distance.b = points.get(j);
                }
            }
        }
        return distance;
    }

    public static Distance getSmallestDistanceSweep(List<Point> points) {

        points.sort((p1, p2) -> (int)(p1.x - p2.x));
        System.out.println(points.size());
        System.out.println(Point.getSmallestDistanceBruteforce(points).distance);

        int ptr = 0;
        
        Point p1 = points.get(ptr);
        Point p2 = points.get(1);

        double distance = Point.calcDistance(p1, p2);

        for(int i = 2; i < points.size(); i++) {
            
            p2 = points.get(i);
            
            int j = 0;
            ArrayList<Point> backtrack_points = new ArrayList<>();
             
            for (int k = ptr; k < i; k++) {
                backtrack_points.add(points.get(k));
            }
            
            System.out.println("------");
            System.out.println(Point.getSmallestDistanceBruteforce(backtrack_points).distance);
            
            
        }
        
        System.out.println(distance);

        return null;
    }


    

}
