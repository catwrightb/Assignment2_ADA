import java.awt.geom.Line2D;
import java.util.ArrayList;

public class BruteForce {
    protected static ArrayList<Coordinate> points;
    static ArrayList<Triangle> triangles;
    protected static int n;
    protected static int iterations;
    static int currentAcrossPoint;
    static int pointsPerPoint;
    static int next;


    public BruteForce(ArrayList<Coordinate> points) {
        this.points = points;
        this.n = points.size();
        this.iterations = 0;
        this.currentAcrossPoint = 1;
        this.pointsPerPoint = points.size() - 3;
        triangles = new ArrayList<>();
        next = (-2 + n) % n;
    }

    public ArrayList<Triangle> startInteriorLineSearch() {
        System.out.println("-------------");
        changeCoordinateMethod(iterations);
        System.out.println(triangles);
        // Collections.sort(distanceBetweenPoints);
        return triangles;

    }

    //this method rotates through the coordinates and calls to the angleChecker method
    public static void changeCoordinateMethod(int i) {
        Boolean intersect = false;

        if (i != 3 ) { //3
            System.out.println(" -------------- Round " + i);
            int last = (i - 1 + n) % n; //last point
            System.out.println(last);
//            int next = (i - 2 + n) % n; //second to last point
            System.out.println(next);
            double x1 = points.get(i).x;
            double y1 = points.get(i).y;
            double x2 = points.get(next).x;
            double y2 = points.get(next).y;
            double x3 = points.get(last).x;
            double y3 = points.get(last).y;

//            for (int j = 0; j < triangles.size(); j++) {
//
//                 if (Line2D.linesIntersect(x1, y1, x2, y2, triangles.get(j).c.x,
//                        triangles.get(j).c.y, triangles.get(j).c.x2, triangles.get(j).c.y2)) {
//                    intersect = true;
//                    break;
//                }
//            }

            if (!intersect){
                polygonAngleCheck(x1, y1, x2, y2, x3, y3, i);
            }

            n--;
            changeCoordinateMethod(i + 1);

//            pointsPerPoint--;
//
//            if (pointsPerPoint == 0) {
//                currentAcrossPoint = 1;
//                pointsPerPoint = points.size() - 3;
//                changeCoordinateMethod(i + 1);
//            } else {
//                currentAcrossPoint++;
//                changeCoordinateMethod(i);
//            }

        }
    }

    public static double polygonAngleCheck ( double x_1, double y_1, double x_2, double y_2, double x_3,
                                             double y_3, int i){

        //Get length of each side
        double c = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2)); // distance from 1 to 2
        double b = Math.sqrt(Math.pow(x_3 - x_2, 2) + Math.pow(y_3 - y_2, 2)); // distance from 2 to 3
        double a = Math.sqrt(Math.pow(x_1 - x_3, 2) + Math.pow(y_1 - y_3, 2)); // distance from 3 to 1

        CoordinateWithDistance aSide = new CoordinateWithDistance((int) x_1, (int) y_1, (int) x_3, (int) y_3, a);
        CoordinateWithDistance bSide = new CoordinateWithDistance((int) x_3, (int) y_3, (int) x_2, (int) y_2, b);
        CoordinateWithDistance cSide = new CoordinateWithDistance((int) x_1, (int) y_1, (int) x_2, (int) y_2, c);

        Triangle triangle = new Triangle(aSide, bSide, cSide, c);

//        //checks that the interior line hasn't already been added an
//        if (!triangles.contains(triangle)) {
//            triangles.add(triangle);
//        }

        triangles.add(triangle);

        return b;

    }

}


