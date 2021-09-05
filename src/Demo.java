import java.awt.*;
import javax.swing.*;

public class Demo extends JPanel{
    public final int PANEL_WIDTH = 500;
    public final int PANEL_HEIGHT = 500;
    //private DrawingPanel drawPanel;  //innerclass
    private String[] values = new String[] {"4", "5", "6", "7", "8", "9"};


    public Demo() {
        super(new BorderLayout());   //invoke super class Jpanel constructor use BorderLayout
        //drawPanel = new DrawingPanel();     //create DrawingPanel and add to center
        //add(drawPanel, BorderLayout.CENTER);
        JPanel topPanel = new JPanel();
        JComboBox<String> testCombo = new JComboBox<String>(values);
        JButton submitButton = new JButton("Submit");
        topPanel.add(testCombo);
        topPanel.add(submitButton);
        setBackground(Color.GREEN);

        JPanel bottomPanel = new JPanel();
        JButton clearButton = new JButton("Clear");
        bottomPanel.add(clearButton, BorderLayout.CENTER);
        setBackground(Color.PINK);

        add(topPanel, BorderLayout.NORTH);
        add(new Shape(PANEL_WIDTH, PANEL_HEIGHT, 5), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Demo myPanel = new Demo();
        JFrame frame = new JFrame("Custom Drawing"); //create frame to hold our JPanel subclass
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        //show the frame
        frame.setVisible(true);
        frame.setResizable(false);

    }

}
