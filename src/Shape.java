import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//TODO need to fix brute force to use the GetInteriorAngle class
//TODO create gui display to show the paths of different approaches, maybe time comparison too

public class Shape extends Component {

    private int width;
    private int height;
    private int n;


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

        int radius = 150;
        int x = width/2;
        int y = height/2;

        //draws circle to check points are along the circle diamemter
        g.setColor(Color.MAGENTA);
        g.drawOval(x-radius, y-radius, 2*radius, 2*radius);

       // point at center of circle
        g.fillOval(x, y, 2,2);

        //gathers coordinates for polygon
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        //back up list that matches the coordinate list
        ArrayList<Coordinate> points = new ArrayList<Coordinate>();
        //collects the distances of the interior edges
        ArrayList<CoordinateWithDistance> distanceBetweenPoints = new ArrayList<>();

        ArrayList<Triangle> triangleslist = new ArrayList<>();


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

        Polygon polygon = new Polygon();

        //creates the exterior edges of the polygon
        for (Coordinate coordinate : coordinates) {
            polygon.addPoint(coordinate.x, coordinate.y);
        }

        g2d.setColor(Color.RED);
        g2d.drawPolygon(polygon);

        BruteForce bf =  new BruteForce(points);
        triangleslist = bf.startInteriorLineSearch();



//        FindInteriorLines bf = new FindInteriorLines(points);
//        distanceBetweenPoints = bf.startInteriorLineSearch();
//        System.out.println("number of lines: " + distanceBetweenPoints.size());
//        System.out.println(distanceBetweenPoints);
//
//        System.out.println(distanceBetweenPoints.get(8));

        //drawLines(g, distanceBetweenPoints);
        for (int i = 0; i < triangleslist.size(); i++) {
            g.setColor(Color.green);
            g.drawLine(triangleslist.get(i).c.x, triangleslist.get(i).c.y, triangleslist.get(i).c.x2, triangleslist.get(i).c.y2);
        }


    }

    public static void drawLines(Graphics g, ArrayList<CoordinateWithDistance> distanceBetweenPoints){

        CoordinateWithDistance line = distanceBetweenPoints.get(0);
        //CoordinateWithDistance line1 = distanceBetweenPoints.get(i+1);

        g.setColor(Color.BLUE);
        g.drawLine(line.x, line.y, line.x2, line.y2);

        for (int j = 1; j < distanceBetweenPoints.size(); j++) {
            if (line.x == distanceBetweenPoints.get(j).x && line.y == distanceBetweenPoints.get(j).y
                    && line.distance != distanceBetweenPoints.get(j).distance){
                CoordinateWithDistance line2 = distanceBetweenPoints.get(j);
                g.setColor(Color.green);
                g.drawLine(line2.x, line2.y, line2.x2, line2.y2);

            }

        }

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
