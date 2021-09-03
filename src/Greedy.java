import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.Collections;

public class Greedy {

    ArrayList<CoordinateWithDistance> lineList;

    public Greedy(ArrayList<CoordinateWithDistance> lineList) {
        this.lineList = lineList;
    }

    public ArrayList<CoordinateWithDistance> startGreedy() {

        //creates a new list to give back all the lines needed to tessellate for this method.
        ArrayList<CoordinateWithDistance> greedyLines = new ArrayList<>();
        //orders the list in descending order for the greedy method.
        Collections.sort(lineList, Collections.reverseOrder());

        while (!lineList.isEmpty()) {
            greedyLines.add(lineList.get(lineList.size() - 1));
//            CoordinateWithDistance temp = lineList.get(lineList.size() - 1);
            for (int i = 0; i < lineList.size(); i++) {
                if ((DoesIntersect(lineList.get(lineList.size() - 1).x
                        , lineList.get(lineList.size() - 1).y
                        , lineList.get(lineList.size() - 1).x2
                        , lineList.get(lineList.size() - 1).y2
                        , lineList.get(i).x, lineList.get(i).y
                        , lineList.get(i).x2, lineList.get(i).y2)
                        && !(greedyLines.get(greedyLines.size() - 1).equals(lineList.get(i))))) {
                    lineList.remove(lineList.get(i));
                    System.out.println(lineList);
                }
            }
            lineList.remove(lineList.size() - 1);
        }
        return greedyLines;
    }

    public int Direction(int xa, int ya, int xb, int yb, int xc, int yc) {
        int val = ((yb - ya) * (xc - xb)) - ((xb - xa) * (yc - yb));
        if (val == 0)
            return 0; // collinear
        else if (val < 0)
            return -1; //anti-clockwise direction
        return 1; //clockwise direction

    }

    /*
    I had to make my own because the one provided in Line2D is not ideal
    as it returns true if the 2 end points are touching and that just ain't
    going to work for us.
     */
    public boolean DoesIntersect(int xa, int ya, int xb, int yb, int xc, int yc, int xd, int yd) {

        int d1 = Direction(xa, ya, xb, yb, xc, yc);
        int d2 = Direction(xa, ya, xb, yb, xd, yd);
        int d3 = Direction(xc, yc, xd, yd, xa, ya);
        int d4 = Direction(xc, yc, xd, yd, xb, yb);

        if (d1 != d3 && d2 != d4) {
            System.out.println(true);
            return true; //this just checks if the 2 line segments straddle one another
        }
        System.out.println(false);
        return false;
    }
}
