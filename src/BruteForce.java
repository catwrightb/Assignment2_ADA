import com.sun.javafx.geom.Line2D;

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
    protected static int n;
    protected static int iterations;
    static int secondToLast;
    static int recursionCurrentPoint;
    static int copyN;
    static ArrayList<Triangle> allTriangleList = new ArrayList<>();
    ArrayList<CoordinateWithDistance> list = new ArrayList<>();



    public BruteForce(ArrayList<Coordinate> points) {
        BruteForce.points = points;
        n = points.size();
        iterations = 0;
    }


    public ArrayList<CoordinateWithDistance> startInteriorLineSearch() {
        //need to clear the list otherwise old points will remain in memory
        list.clear();
        allTriangleList.clear();

        copyN = n;

        //for loop will rotate around the points of the polygon
        for (int i = 0; i < copyN; i++) {
            secondToLast = (i - 2 + copyN) % copyN;
            recursionCurrentPoint = i;
            n = copyN;
            changeCoordinateMethod(iterations);
        }

        //sort the found edges from least to greatest distance
        Collections.sort(allTriangleList);

        //loop through the list
        for (int i = 0; i < allTriangleList.size(); i++) {
            boolean inter = false;

            //adds first smallest triangle
            if (list.isEmpty()){
                list.add(allTriangleList.get(i).c);
            }
            else {
                CoordinateWithDistance c3 = allTriangleList.get(i).c; //get index at i position

                //then compare index at i with coordinates in the list
                for (int j = 0; j < list.size(); j++) {
                    CoordinateWithDistance temp = list.get(j);

                    //if the coordinates are the same
                    if ((temp.x == c3.x && temp.y == c3.y && temp.x2 == c3.x2 && temp.y2 == c3.y2) ||
                            (temp.x == c3.x2 && temp.y == c3.y2 && temp.x2 == c3.x && temp.y2 == c3.y) ||
                            (temp.distance == c3.distance)){
                        inter = true;
                    }
                    else { //if coordinate are different
                        //this if statement doesnt always work and i have no idea why
                        // i have checked the lineintersect method and it works but cases still get through
                        // the coordiantes are off by one in order to avoid cases where the points are the same like (50, 100) and (50,100)
                        inter = Line2D.linesIntersect(c3.x + 1, c3.y + 1, c3.x2 + 1, c3.y2 + 1, temp.x, temp.y, temp.x2, temp.y2);
                    }
                }

                if (!inter){
                    list.add(c3);
                }
            }

        }

        //back up check if brute force cant find the correct number of interior edges
        if (list.size() != copyN -3){
            list.clear();
            CoordinateWithDistance c = allTriangleList.get(0).c;
            list.add(c);

            for (int i = 0; i < allTriangleList.size(); i++) {
                CoordinateWithDistance c1 = allTriangleList.get(i).c;

                if (c.x == c1.x && c.y == c1.y){
                    if ((c.distance != c1.distance)){
                        list.add(c1);
                    }
                }
            }
        }

//        System.out.println("List: "+list);

        return list;
    }



    //this method recursively finds all triangles possible from one point of the polygon
    public static void changeCoordinateMethod(int i) {

        if (i != copyN-3 ) {
            int last = (recursionCurrentPoint - 1 + n) % n; //last point
            double x1 = points.get(recursionCurrentPoint).x;
            double y1 = points.get(recursionCurrentPoint).y;
            double x2 = points.get(secondToLast).x;
            double y2 = points.get(secondToLast).y;
            double x3 = points.get(last).x;
            double y3 = points.get(last).y;


            polygonAngleCheck(x1, y1, x2, y2, x3, y3);


            n--;
            recursionCurrentPoint++;
            if (recursionCurrentPoint == copyN){
                recursionCurrentPoint = 0;
            }
            changeCoordinateMethod(i + 1);

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

        allTriangleList.add(triangle);

    }

}


