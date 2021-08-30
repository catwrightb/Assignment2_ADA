import java.util.ArrayList;

public class BruteForce {
    protected static ArrayList<Coordinate> points;
    static ArrayList<CoordinateWithDistance> distanceBetweenPoints = new ArrayList<>();
    protected static int n;
    protected static int i;
    static int end_n;
    static double sum;

    public BruteForce(ArrayList<Coordinate> points) {
        this.points = points;
        this.n = points.size();
        this.i = 0;
        this.end_n = n-2;
        sum = 0;
        startBruteForce();

    }

    public static void startBruteForce(){
        System.out.println("-------------");
        changeCoordinateMethod(i);
        System.out.println(sum);

    }

    //this method rotates through the coordinates and calls to the angleChecker method
    public static void changeCoordinateMethod( int i){
        if (i != end_n) {
            int last = (i - 1 + n) % n;
            int next = (i + 1) % n;
            double x1 = points.get(i).x;
            double y1 = points.get(i).y;
            double x2 = points.get(next).x ;
            double y2 = points.get(next).y;
            double x3 = points.get(last).x ;
            double y3 = points.get(last).y ;

            sum += polygonAngleCheck(x1,y1,x2,y2,x3,y3, i);

            changeCoordinateMethod(i+1);
        }

    }

    public static double polygonAngleCheck(double x_1, double y_1, double x_2,  double y_2, double x_3,
                                           double y_3 , int i){

        double a = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2)); // distance from 1 to 2
        double b = Math.sqrt(Math.pow(x_3 - x_2, 2) + Math.pow(y_3 - y_2, 2)); // distance from 2 to 3
        //System.out.println("distance from ("+x_2+", "+y_2+") and ("+x_3+", "+y_3+") : "+b);
        double c = Math.sqrt(Math.pow(x_1 - x_3, 2) + Math.pow(y_1 - y_3, 2)); // distance from 3 to 1
        distanceBetweenPoints.add(new CoordinateWithDistance( (int)x_2, (int)y_2, (int)x_3, (int)y_3, b));
        //Get angles ***
        double triangleAngle1 = Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)));
        double triangleAngle2 = Math.toDegrees(Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * c * b)));
        double triangleAngle3 = Math.toDegrees(Math.acos((Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2)) / (2 * a * c)));

        return triangleAngle1 + triangleAngle2 + triangleAngle3;
    }

}
