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
        Collections.sort(lineList, Collections.reverseOrder());

        while (!lineList.isEmpty()) {
            greedyLines.add(lineList.get(lineList.size() - 1));
            CoordinateWithDistance temp = lineList.get(lineList.size() - 1);
            lineList.removeIf(line -> Line2D.linesIntersect(
                    temp.x, temp.y, temp.x2, temp.y2,
                    line.x, line.y, line.x2, line.y2));
        }
        return greedyLines;
    }
}
