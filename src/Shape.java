import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.*;

public class Shape extends Component {

    int width;
    int height;
     int n;
    //static double interiorAngleSum;
   // static ArrayList<CoordinateWithDistance> distanceBetweenPoints;
    ArrayList<Coordinate> coordinates;
    ArrayList<Coordinate> points;

    public Shape(int PANEL_WIDTH, int PANEL_HEIGHT, int sides) {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        width = PANEL_WIDTH;
        height = PANEL_HEIGHT;
        n = sides;

    }

    public void paint(Graphics g) {

        //System.out.println("-----------------------");
        // Retrieve the graphics context; this object is used to paint shapes
        Graphics2D g2d = (Graphics2D) g;

        int radius = 100;
        int x = width/2;
        int y = height/2;

        //draws circle to check points are along the circle diamemter
        g.setColor(Color.MAGENTA);
        g.drawOval(x-radius, y-radius, 2*radius, 2*radius);

       // point at center of circle
        g.fillOval(x, y, 2,2);

        coordinates = new ArrayList<Coordinate>();
        points = new ArrayList<Coordinate>();
        //distanceBetweenPoints = new ArrayList<CoordinateWithDistance>();

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
      //  System.out.println(coordinates);


        //changeCoordinateMethod(points);

        Polygon polygon = new Polygon();

        //creates the exterior edges of the polygon
        for (Coordinate coordinate : coordinates) {
            polygon.addPoint(coordinate.x, coordinate.y);
        }

        g2d.setColor(Color.RED);
        g2d.drawPolygon(polygon);

        BruteForce bf = new BruteForce(points);


//        Collections.sort(distanceBetweenPoints);
//        for (int i = 0; i < n-3; i++) {
//            CoordinateWithDistance line = distanceBetweenPoints.get(i);
//            g.setColor(Color.BLUE);
//            g.drawLine(line.x, line.y, line.x2, line.y2);
//        }

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


}
