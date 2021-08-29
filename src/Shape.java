import java.awt.*;

public class Shape extends Component {

    int width;
    int height;

    public Shape(int PANEL_WIDTH, int PANEL_HEIGHT) {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        width = PANEL_WIDTH;
        height = PANEL_HEIGHT;
        //setBackground(Color.GREEN);

    }

    public void paint(Graphics g) {

        // Retrieve the graphics context; this object is used to paint shapes

        Graphics2D g2d = (Graphics2D) g;

        // Draw an oval that fills the window

        int min = 50;
        int max = 300;
        int c = (int)(Math.random()*(max-min+1)+min);

        int x = 0;
        int y = 0;
        int w = getSize().width - 1;
        int h = getSize().height - 1;


        Polygon polygon = new Polygon();

        int a = (width/2)-50;
        int b= (height/2)-50;
        int z =100;
        int p = 75;

        polygon.addPoint(a, b);
        polygon.addPoint(a+z, b);

        polygon.addPoint(a+p, b+z);
        polygon.addPoint(a, b+z);


        g2d.drawPolygon(polygon);

        g.setColor(Color.blue);
        g.drawLine(a,b,a+z,b+z);

    }


}
