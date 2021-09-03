
import java.awt.geom.Line2D;
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
        Collections.sort(lineList);

        while (!lineList.isEmpty()) {

            CoordinateWithDistance temp = lineList.get(0);
            //adds the smallest line to the new list
            greedyLines.add(temp);

            //checks first if the points are the same then if they are not the same checks if there is an intersection
            lineList.removeIf(line -> !(temp.x == line.x && temp.y == line.y
                    || temp.x2 == line.x2 && temp.y2 == line.y2
                    || temp.x == line.x2 && temp.y == line.y2
                    || temp.x2 == line.x && temp.y2 == line.y)
                    && (Line2D.linesIntersect(temp.x, temp.y, temp.x2, temp.y2
                    , line.x, line.y, line.x2, line.y2)));

            //removes the previous smallest line from the list
            lineList.remove(temp);
        }

        return greedyLines;
    }
}
