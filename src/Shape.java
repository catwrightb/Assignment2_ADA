import com.sun.javafx.geom.Line2D;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;


public class Shape extends JPanel {

    private int width;
    private int height;
    private int n;
    private String[] values = new String[]{"4", "5", "6", "7", "8", "9", "10", "13", "16", "18", "20"};
    int number = 0;
    boolean clicked = false;
    static JLabel bruteFinding;
    static JLabel greedyFinding;
    static JLabel exactFinding;
    static JLabel otherInfo;
    static String bruteString = "(Black Line) Brute : ";
    static String greedyString = "(Green Line) Greedy : ";
    static String exactString = "(Red Line) Exact : ";


    public Shape(int PANEL_WIDTH, int PANEL_HEIGHT) {
        super(new BorderLayout());   //invoke super class Jpanel constructor use BorderLayout
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        JComboBox<String> testCombo = new JComboBox<String>(values);

        testCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String numberOfSides = Objects.requireNonNull(testCombo.getSelectedItem()).toString();

                number = Integer.parseInt(numberOfSides);
                System.out.println(number);
                n = number;
            }
        });

        JButton submitButton = new JButton("Submit");
        otherInfo = new JLabel("P.S: Tesselation lines will appear over each other");



        JPanel labelPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(labelPanel,BoxLayout.PAGE_AXIS );
        labelPanel.setLayout(boxLayout);

        bruteFinding = new JLabel(bruteString);
        greedyFinding = new JLabel(greedyString);
        exactFinding = new JLabel(exactString);


        labelPanel.add(bruteFinding);
        labelPanel.add(greedyFinding);
        labelPanel.add(exactFinding);



        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (number != 0) {
                    clicked = true;
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a number of sides option",
                            "Number of sides is 0!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        topPanel.add(testCombo);
        topPanel.add(submitButton);
        topPanel.add(otherInfo);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setSize(30, 20);
        JButton clearButton = new JButton("Clear");
        // clearButton.setSize(30,20);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = 0;
                clicked = false;
                bruteString = "(Black Line) Brute : ";
                bruteFinding.setText(bruteString);
                greedyString = "(Green Line) Greedy : ";
                greedyFinding.setText(greedyString);
                exactString = "(Red Line) Exact : ";
                exactFinding.setText(exactString);
                repaint();
            }
        });

        //bottomPanel.add(clearButton, BorderLayout.NORTH);
        bottomPanel.add(clearButton);


        add(topPanel, BorderLayout.NORTH);
        add(labelPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        width = PANEL_WIDTH;
        height = PANEL_HEIGHT;

    }


    //paintComponent will paint 2 shapes fyi only one is seen though
    // this is something we cant control
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("Paint");
        // Retrieve the graphics context; this object is used to paint shapes
        Graphics2D g2d = (Graphics2D) g;

        if (clicked) {
            int radius = 220;
            int x = width / 2;
            int y = height / 2;

            //draws circle to check points are along the circle diamemter
//            g.setColor(Color.MAGENTA);
//            g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
//
//            // point at center of circle
//            g.fillOval(x, y, 2, 2);

            //gathers coordinates for polygon
            ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
            //back up list that matches the coordinate list
            ArrayList<Coordinate> points = new ArrayList<Coordinate>();
            //collects the distances of the interior edges
            ArrayList<CoordinateWithDistance> distanceBetweenPoints = new ArrayList<>();

            ArrayList<CoordinateWithDistance> triangleslist = new ArrayList<>();


            int m = Math.min(x, y);
            int c = 4 * m / 5;
            //creates radius for points on the circle
            int r2 = Math.abs(m - c) / 9;
            Random random = new Random();

            for (int i = 0; i < n; i++) {
                //triangulates the points on the circle
                //double t = 2 * Math.PI * i / n;
                double t = random.nextDouble() * Math.PI * 2;
                int a = (int) Math.round(x + radius * Math.cos(t));
                int b = (int) Math.round(y + radius * Math.sin(t));
                g.setColor(Color.DARK_GRAY);

                g.fillOval(a - r2, b - r2, 2 * r2, 2 * r2);
                coordinates.add(new Coordinate(a, b));
                points.add(new Coordinate(a, b));
            }

            sortVerticies(coordinates);
            sortVerticies(points);

            Polygon polygon = new Polygon();

            //creates the exterior edges of the polygon
            for (Coordinate coordinate : coordinates) {
                polygon.addPoint(coordinate.x, coordinate.y);
            }

            g2d.drawPolygon(polygon);


            //Brute Force
            BruteForce bruteforce = new BruteForce(points);
            triangleslist = bruteforce.startInteriorLineSearch();

            String s1 = sumDistances(triangleslist);
            String newString1 = bruteString.concat(s1);
            bruteFinding.setText(newString1);

            drawLines(g2d, triangleslist, Color.BLACK, 5);

            //Greedy
            FindInteriorLines fi = new FindInteriorLines(points);
            distanceBetweenPoints = fi.startInteriorLineSearch();
            Greedy greedy = new Greedy(distanceBetweenPoints);
            ArrayList<CoordinateWithDistance>  greedyCoordinates = greedy.startGreedy();

            String s2 = sumDistances(greedyCoordinates);
            String newString2 = greedyString.concat(s2);
            greedyFinding.setText(newString2);

            drawLines(g2d, greedyCoordinates, Color.GREEN, 3);

            //Exact
            ArrayList<Triangle> tList = new ArrayList<>();
            ExactMethod exactMethod = new ExactMethod();
            Double d = exactMethod.startExact(points, points.size());
            d /= n;
            double roundOff = Math.round(d*100)/100;

            String s3 = String.valueOf(roundOff);
            String newString3 = exactString.concat(s3);
            exactFinding.setText(newString3);
            drawLines(g2d, triangleslist, Color.RED, 1);
            //tList = exactMethod.getcTable();


        }
    }




    public static String sumDistances(ArrayList<CoordinateWithDistance> points){
        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            CoordinateWithDistance coordinateWithDistance = points.get(i);
            sum += coordinateWithDistance.distance;
        }
        sum /= points.size();

        double roundOff = Math.round(sum*100)/100;

        return String.valueOf(roundOff);

    }


    public static void drawLines(Graphics2D g2d, ArrayList<CoordinateWithDistance> interiorEdges, Color Black, int five) {

        for (CoordinateWithDistance interiorEdge : interiorEdges) {
            g2d.setColor(Black);
            g2d.setStroke(new BasicStroke(five));
            g2d.drawLine(interiorEdge.x, interiorEdge.y, interiorEdge.x2, interiorEdge.y2);

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
