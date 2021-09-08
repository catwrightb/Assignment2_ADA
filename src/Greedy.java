import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;

/*
for this method the way I understand is that the greedy method takes a sorted list
of lines that go between all points in the polygon and finds the smallest possible
cost for each line drawn between 2 points in the polygon then removes all the
other lines that intersected the previously chosen the smallest line. then continues
to do this till there are not more lines in the list. this should ultimately give the
greedy method answer.
 */

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
            //and removes the line if an intersection is found.
            lineList.removeIf(line -> !(temp.x == line.x && temp.y == line.y
                    || temp.x2 == line.x2 && temp.y2 == line.y2
                    || temp.x == line.x2 && temp.y == line.y2
                    || temp.x2 == line.x && temp.y2 == line.y)
                    && (Line2D.linesIntersect(temp.x, temp.y, temp.x2, temp.y2
                    , line.x, line.y, line.x2, line.y2)));

            //removes the previous smallest line from the list
            lineList.remove(temp);
        }

        System.out.println(greedyLines);
        return greedyLines;
    }
}
