import com.sun.javafx.geom.Line2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


/*
* Brute force is exactly what it sounds like, it is finding all possible tessellations per point
* Because the algorithm doesn't care about preventing repeats and avoiding already done work
* it often repeats itself and is not very efficient for large n sided polygons. Recursion is used in this approach.
* */

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
    static ArrayList<Triangle> allTriangleList = new ArrayList<>();
    double d = 0.0;
    int smallest = 0;
    double t = 0;
    ArrayList<CoordinateWithDistance> list = new ArrayList<>();
    Stack<Triangle> stack = new Stack<>();


    public BruteForce(ArrayList<Coordinate> points) {
        this.points = points;
        this.pointsTest = points;
        this.n = points.size();
        this.iterations = 0;
        this.pointsPerPoint = points.size() - 3;
        triangles = new ArrayList<>();
    }


    public ArrayList<CoordinateWithDistance> startInteriorLineSearch() {
        //need to clear the list otherwise old points will remain in memory
        triangles.clear();
        testList.clear();
        list.clear();
        stack.clear();
        allTriangleList.clear();

        f = n;

        for (int i = 0; i < f-2; i++) {
            triangles = new ArrayList<>();
            secondToLast = (i - 2 + f) % f;
            j = i;
            n = f;
            changeCoordinateMethod(iterations);
            testList.add(triangles);
        }

//        if (f % 2 == 0){
//            for (int i = 0; i < 1; i++) {
//                triangles = new ArrayList<>();
//                j = i;
//                n = f;
//                changeCoordinateMethodSideways(iterations);
//                testList.add(triangles);
//
//            }
//        }

//        for (int i = 0; i < testList.size(); i++) {
//
//            for (int k = 0; k < n-2; k++) {
//                d += testList.get(i).get(k).hypothenus;
//            }
//
//            d /= f-3;
//
//            if (i == 0){
//                t = d;
//            }
//            else if (d < t){
//                smallest = i;
//            }
//
//            d = 0.0;
//        }

        System.out.println("----------------------------------------------------------------------");
        System.out.println(allTriangleList);
        System.out.println("----------------------------------------------------------------------");
        Collections.sort(allTriangleList);
        System.out.println("----------------------------------------------------------------------");
        System.out.println(allTriangleList);
        System.out.println("----------------------------------------------------------------------");





        for (int i = 0; i < allTriangleList.size(); i++) {

            if (list.isEmpty()){ //adds first smallest triangle
                list.add(allTriangleList.get(i).c);
            }
            else {
                CoordinateWithDistance c = allTriangleList.get(i).c;


                for (int k = 0; k < list.size(); k++) {
                    CoordinateWithDistance o = list.get(k);

                    if (!list.contains(c)){
                        if (!Line2D.linesIntersect(c.x-1,c.y-1,c.x2-1,c.y2-1,o.x,o.y,o.x2,o.y2)){
                            stack.add(allTriangleList.get(i));
                        }
                        else {
                            if (!stack.isEmpty()){
                                System.out.println("cleared stack");
                                stack.clear();
                            }
                        }
                    }

                }

                if (!stack.isEmpty()){
                    list.add(stack.get(0).c);
                    stack.clear();
                }
                stack.clear();

            }

            if (list.size() == f-3){
                System.out.println("break");
                break;
            }

        }


        System.out.println("List: "+list);

        return list;

        //System.out.println("Smallest = "+ smallest);
        //return testList.get(smallest);


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
                j=0;
            }
            changeCoordinateMethod(i + 1);

        }
    }




    //this method is used for checking side triangle creation
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
            pointsTest.add(cSide);
            allTriangleList.add(triangle);

        }

    }

}


