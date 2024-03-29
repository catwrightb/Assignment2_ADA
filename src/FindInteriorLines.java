import java.util.ArrayList;
import java.util.Collections;

public class FindInteriorLines {
    protected static ArrayList<Coordinate> points;
    static ArrayList<CoordinateWithDistance> distanceBetweenPoints;
    protected static int n;
    protected static int iterations;
    static int currentAcrossPoint;
    static int pointsPerPoint;

    public FindInteriorLines(ArrayList<Coordinate> points) {
        distanceBetweenPoints = new ArrayList<>();
        this.points = points;
        this.n = points.size();
        this.iterations = 0;
        this.currentAcrossPoint = 2;
        this.pointsPerPoint = points.size()-3;
    }

    public ArrayList<CoordinateWithDistance> startInteriorLineSearch(){
        System.out.println("-------------");
        changeCoordinateMethod(iterations);

        return distanceBetweenPoints;

    }

    //this method rotates through the coordinates and calls to the angleChecker method
    public static void changeCoordinateMethod( int i){

        if (i != n-2) { //3
            int across = (i + currentAcrossPoint) % n;
            double x1 = points.get(i).x;
            double y1 = points.get(i).y;
            double x2 = points.get(across).x ;
            double y2 = points.get(across).y;

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

        //checks that the interior line hasn't already been added an
        if (!distanceBetweenPoints.contains(coord)){
            distanceBetweenPoints.add(coord);
        }

    }





}
