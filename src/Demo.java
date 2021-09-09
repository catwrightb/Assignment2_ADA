import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

public class Demo extends JFrame{
    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 600;
    //private DrawingPanel drawPanel;  //innerclass



    public Demo(int sides) {

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Generator"); //create frame to hold our JPanel subclass
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,700) );
        frame.getContentPane().add(new Shape(PANEL_WIDTH, PANEL_HEIGHT));  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        //show the frame
        frame.setVisible(true);
        frame.setResizable(true);

    }


}
