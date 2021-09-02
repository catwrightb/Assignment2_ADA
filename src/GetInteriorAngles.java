import java.util.ArrayList;
import java.util.Collections;

public class GetInteriorAngles {
    protected static ArrayList<Coordinate> points;
    static ArrayList<CoordinateWithDistance> distanceBetweenPoints;
    protected static int n;
    protected static int iterations;
    static int currentAcrossPoint;
    static int pointsPerPoint;

    public GetInteriorAngles(ArrayList<Coordinate> points) {
        distanceBetweenPoints = new ArrayList<>();
        this.points = points;
        this.n = points.size();
        this.iterations = 0;
        this.currentAcrossPoint = 2;
        this.pointsPerPoint = points.size()-3;
    }

    public ArrayList<CoordinateWithDistance> getAngles(){
        System.out.println("-------------");
        changeCoordinateMethod(iterations);
       // System.out.println(sum);
        Collections.sort(distanceBetweenPoints);
        return distanceBetweenPoints;

    }

    //this method rotates through the coordinates and calls to the angleChecker method
    public static void changeCoordinateMethod( int i){

        if (i != n-2) { //3
            //int last = (i - 2 + n) % n;
            int across = (i + currentAcrossPoint) % n;
            double x1 = points.get(i).x;
            double y1 = points.get(i).y;
            double x2 = points.get(across).x ;
            double y2 = points.get(across).y;
//            double x3 = points.get(last).x ;
//            double y3 = points.get(last).y ;

           polygonAngleCheck(x1,y1,x2,y2);
           pointsPerPoint--;

           if(pointsPerPoint == 0){
               currentAcrossPoint = 2;
               pointsPerPoint = points.size()-3;
               changeCoordinateMethod(i+1);
           }
           else {
               currentAcrossPoint++;
               changeCoordinateMethod(i);
           }

        }

    }

    public static void polygonAngleCheck(double x_1, double y_1, double x_2,  double y_2){
        double a = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2));
        CoordinateWithDistance coord = new CoordinateWithDistance((int)x_1, (int)y_1, (int)x_2, (int)y_2, a);

        if (!distanceBetweenPoints.contains(coord)){
            distanceBetweenPoints.add(coord);
        }

        //code to get angles below, not particularly useful currently
//        double a = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2)); // distance from 1 to 2
//        distanceBetweenPoints.add(new CoordinateWithDistance( (int)x_1, (int)y_1, (int)x_2, (int)y_2, a));
////        double b = Math.sqrt(Math.pow(x_3 - x_2, 2) + Math.pow(y_3 - y_2, 2)); // distance from 2 to 3
////        double c = Math.sqrt(Math.pow(x_1 - x_3, 2) + Math.pow(y_1 - y_3, 2)); // distance from 3 to 1
////        //distanceBetweenPoints.add(new CoordinateWithDistance( (int)x_1, (int)y_1, (int)x_3, (int)y_3, c));
////        addingToList(x_1, y_1, x_3, y_3, c);
//        //Get angles ***
//        double triangleAngle1 = Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)));
//        double triangleAngle2 = Math.toDegrees(Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * c * b)));
//        double triangleAngle3 = Math.toDegrees(Math.acos((Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2)) / (2 * a * c)));
//
////        distanceBetweenPoints.contains()

//        return triangleAngle1 + triangleAngle2 + triangleAngle3;
    }





}
