import java.util.ArrayList;

public class BruteForce {
    protected static ArrayList<Coordinate> points;
    protected static ArrayList<Coordinate> pointsTest;
    static ArrayList<Triangle> triangles;
    protected static int n;
    protected static int iterations;
    static int pointsPerPoint;
    static int secondToLast;
    static int j;
    static int f;
    static ArrayList< ArrayList<Triangle>> testList = new ArrayList<>();
    double d = 0.0;
    int smallest = 0;
    double t = 0;


    public BruteForce(ArrayList<Coordinate> points) {
        this.points = points;
        this.pointsTest = points;
        this.n = points.size();
        this.iterations = 0;
        this.pointsPerPoint = points.size() - 3;
        triangles = new ArrayList<>();
    }


    public ArrayList<Triangle> startInteriorLineSearch() {
        //need to clear the list otherwise old points will remain in memory
        triangles.clear();
        testList.clear();

        f = n;

        for (int i = 0; i < f-2; i++) {
            triangles = new ArrayList<>();
            secondToLast = (i - 2 + f) % f;
            j = i;
            n = f;
            changeCoordinateMethod(iterations);
            testList.add(triangles);
        }

        if (f == 6){
            for (int i = 0; i < 1; i++) {
                triangles = new ArrayList<>();
                j = i;
                n = f;
                changeCoordinateMethodSideways(iterations);
                testList.add(triangles);

            }
        }

        for (int i = 0; i < testList.size(); i++) {

            for (int k = 0; k < n-2; k++) {
                d += testList.get(i).get(k).hypothenus;
            }

            d /= f-3;

            if (i == 0){
                t = d;
            }
            else if (d < t){
                smallest = i;
            }

            d = 0.0;
        }
        //System.out.println("Smallest = "+smallest);
        return testList.get(smallest);


    }



    //this method rotates through the coordinates and calls to the angleChecker method
    public static void changeCoordinateMethod(int i) {
        Boolean intersect = false;

        if (i != f-3 ) { //3
            int last = (j - 1 + n) % n; //last point
            double x1 = points.get(j).x;
            double y1 = points.get(j).y;
            double x2 = points.get(secondToLast).x;
            double y2 = points.get(secondToLast).y;
            double x3 = points.get(last).x;
            double y3 = points.get(last).y;

            if (!intersect){
                polygonAngleCheck(x1, y1, x2, y2, x3, y3);
            }

            n--;
            j++;
            if (j==f){
                System.out.println("Reset J");
                j=0;
            }
            changeCoordinateMethod(i + 1);

        }
    }




    //this method rotates through the coordinates and calls to the angleChecker method
    public static void changeCoordinateMethodSideways(int i) {

        if ((i != f-3)) { //3

            int last = (j - 1 + n) % n; //last point
            int second = (j - 2 + n) % n; //second to last point
            double x1 = points.get(j).x;
            double y1 = points.get(j).y;
            double x2 = points.get(second).x;
            double y2 = points.get(second).y;
            double x3 = points.get(last).x;
            double y3 = points.get(last).y;

            polygonAngleCheck(x1, y1, x2, y2, x3, y3);

            n--;
            j+=2;

            if (j >= f){
                j=0;
            }
            changeCoordinateMethodSideways(i + 1);

        }
    }

    public static void polygonAngleCheck ( double x_1, double y_1, double x_2, double y_2, double x_3,
                                             double y_3){

        //Get length of each side
        double c = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2)); // distance from 1 to 2
        double b = Math.sqrt(Math.pow(x_3 - x_2, 2) + Math.pow(y_3 - y_2, 2)); // distance from 2 to 3
        double a = Math.sqrt(Math.pow(x_1 - x_3, 2) + Math.pow(y_1 - y_3, 2)); // distance from 3 to 1

        CoordinateWithDistance aSide = new CoordinateWithDistance((int) x_1, (int) y_1, (int) x_3, (int) y_3, a);
        CoordinateWithDistance bSide = new CoordinateWithDistance((int) x_3, (int) y_3, (int) x_2, (int) y_2, b);
        CoordinateWithDistance cSide = new CoordinateWithDistance((int) x_1, (int) y_1, (int) x_2, (int) y_2, c);

        Triangle triangle = new Triangle(aSide, bSide, cSide, c);


        if (!triangles.contains(triangle)) {
            triangles.add(triangle);

        }

    }

}


