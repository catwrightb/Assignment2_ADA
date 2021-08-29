import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Shape extends Component {

    int width;
    int height;
    int n;

    public Shape(int PANEL_WIDTH, int PANEL_HEIGHT, int sides) {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        width = PANEL_WIDTH;
        height = PANEL_HEIGHT;
        n = sides;
        //setBackground(Color.GREEN);

    }

    public void paint(Graphics g) {

        // Retrieve the graphics context; this object is used to paint shapes
        Graphics2D g2d = (Graphics2D) g;

        int radius = 100;
        int x = width/2;
        int y = height/2;

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        //draws circle
        g.setColor(Color.MAGENTA);
        g.drawOval(x-radius, y-radius, 2*radius, 2*radius);

        //point at center of circle
        g.fillOval(x, y, 2,2);

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
        }

        sortVerticies(coordinates);

        Polygon polygon = new Polygon();

        for (int i = 0; i < coordinates.size(); i++) {
            polygon.addPoint(coordinates.get(i).x, coordinates.get(i).y);
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

    public static ArrayList<Coordinate> sortVerticies(ArrayList<Coordinate> points) {
        // get centroid
        Point center = findCentroid(points);
        Collections.sort(points, (a, b) -> {
            double a1 = (Math.toDegrees(Math.atan2(a.x - center.x, a.y - center.y)) + 360) % 360;
            double a2 = (Math.toDegrees(Math.atan2(b.x - center.x, b.y - center.y)) + 360) % 360;
            return (int) (a1 - a2);
        });
        return points;
    }

}
