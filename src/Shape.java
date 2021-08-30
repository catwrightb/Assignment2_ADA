import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.*;

public class Shape extends Component {

    int width;
    int height;
    static int n;
    static double interiorAngleSum;

    public Shape(int PANEL_WIDTH, int PANEL_HEIGHT, int sides) {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        width = PANEL_WIDTH;
        height = PANEL_HEIGHT;
        n = sides;
        //setBackground(Color.GREEN);

    }


    public void paint(Graphics g) {

        System.out.println("-----------------------");
        // Retrieve the graphics context; this object is used to paint shapes
        Graphics2D g2d = (Graphics2D) g;

        int radius = 100;
        int x = width/2;
        int y = height/2;

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        ArrayList<Coordinate> points = new ArrayList<Coordinate>();

       // draws circle to check points are along the circle diamemter
//        g.setColor(Color.MAGENTA);
//        g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
//
//       // point at center of circle
//        g.fillOval(x, y, 2,2);

        int m = Math.min(x, y);
        int c = 4 * m / 5;
        //creates radius for points on the circle
        int r2 = Math.abs(m - c) / 9;
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            //triangulates the points on the circle
           //double t = 2 * Math.PI * i / n;
            double t = random.nextDouble() * Math.PI*2;
            int a = (int) Math.round(x + radius *Math.cos(t));
            int b = (int) Math.round(y + radius *Math.sin(t));
            g.setColor(Color.BLACK);
            g.fillOval(a-r2, b-r2, 2*r2, 2*r2);
            coordinates.add(new Coordinate(a,b));
            points.add(new Coordinate(a,b));
        }

        sortVerticies(coordinates);
        sortVerticies(points);
        System.out.println(coordinates);

        System.out.println("--------------------------------------");
        changeCoordinateMethod(points);

        Polygon polygon = new Polygon();

        //creates the exterior edges of the polygon
        for (Coordinate coordinate : coordinates) {
            polygon.addPoint(coordinate.x, coordinate.y);
        }

        g2d.setColor(Color.RED);
        g2d.drawPolygon(polygon);

    }

    public static Point findCentroid(ArrayList<Coordinate> points) {
        int x = 0;
        int y = 0;
        for (Coordinate p : points) {
            x += p.x;
            y += p.y;
        }
        Point center = new Point(0, 0);
        center.x = x / points.size();
        center.y = y / points.size();
        return center;
    }

    public static void sortVerticies(ArrayList<Coordinate> points) {
        // get centroid
        Point center = findCentroid(points);
        //sorts the points clockwise
        Collections.sort(points, (a, b) -> {
            //tan angle of the coordinate to the center
            double a1 = (Math.toDegrees(Math.atan2(a.x - center.x, a.y - center.y)) + 360) % 360;
            double a2 = (Math.toDegrees(Math.atan2(b.x - center.x, b.y - center.y)) + 360) % 360;
            return (int) (a2 - a1);
        });
    }

    //this method rotates through the coordinates and calls to the angleChecker method
    public static void changeCoordinateMethod(ArrayList<Coordinate> points){
        ArrayList<Double> total = new ArrayList<>();

        for(int i = 0; i < n-2; i++){
            int last = (i - 1 + n) % n;
            int next = (i + 1) % n;
            double x1 = points.get(i).x;
            double y1 = points.get(i).y;
            double x2 = points.get(next).x ;
            double y2 = points.get(next).y;
            double x3 = points.get(last).x ;
            double y3 = points.get(last).y ;

            total.add(polygonAngleCheck(x1,y1,x2,y2,x3,y3, i));
        }

        double sum = 0;
        for (Double aDouble : total) {
            sum += aDouble;
        }

        double roundOff = Math.round(sum * 100.0) / 100.0;
        interiorAngleSum = roundOff;
        System.out.println(roundOff);
    }



    public static double polygonAngleCheck(double x_1, double y_1, double x_2,  double y_2, double x_3,
                                         double y_3 , int i){

//        System.out.println(i + " round");
//        System.out.println("The coordinates of the three points are: "+
//                ("(" + x_1 + ", " + y_1 +"), ")+
//                ("("+ x_2 + ", " + y_2+"), ")+
//                ("("+ x_3 + ", " + y_3+")"));
        //Get length of each side
        double a = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2)); // distance from 1 to 2
        double b = Math.sqrt(Math.pow(x_3 - x_2, 2) + Math.pow(y_3 - y_2, 2)); // distance from 2 to 3
        double c = Math.sqrt(Math.pow(x_1 - x_3, 2) + Math.pow(y_1 - y_3, 2)); // distance from 3 to 1
        //Get angles ***
        double triangleAngle1 = Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)));
        double triangleAngle2 = Math.toDegrees(Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * c * b)));
        double triangleAngle3 = Math.toDegrees(Math.acos((Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2)) / (2 * a * c)));

//        System.out.println("The three angles are " + triangleAngle1 + " " +
//                triangleAngle2 + " " + triangleAngle3);
//        System.out.println(triangleAngle1 + triangleAngle2 + triangleAngle3);

        return triangleAngle1 + triangleAngle2 + triangleAngle3;

    }

}
