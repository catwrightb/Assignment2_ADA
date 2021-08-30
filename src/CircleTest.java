import javax.swing.*;
import java.awt.*;

//test class to practice circle drawing and getting points on it
//remove before turning in

public class CircleTest extends JPanel {

    private static final int SIZE = 300;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private int n;

    /** @param n  the desired number of circles. */
    public CircleTest(int n) {
        super(true);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.n = n;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.black);
        a = getWidth() / 2;
        b = getHeight() / 2;
        int m = Math.min(a, b);
        r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 5;
        g2d.drawOval(a - r, b - r, 2 * r, 2 * r);
        g2d.fillOval(a,b,4,4);
        g2d.setColor(Color.blue);
        for (int i = 0; i < n; i++) {
            double t = 2 * Math.PI * i / n;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            g2d.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
            //g2d.drawLine(x - r2, y - r2, 2 * r2, 2 * r2);
        }
    }

    private static void create() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new CircleTest(10));
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                create();
            }
        });
    }
}