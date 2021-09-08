import java.util.ArrayList;
import java.util.Arrays;
/*
 * for how the solving for the dynamic exact method it would be best to visualise
 * using the below image.
 *
 *  |  a      |b      |c      |d      |e      |f
 * -|---------|-------|-------|-------|-------|------
 * a|  0      |0      |abc    |abcd   |abcde  |abcdef
 *  |---------|-------|-------|-------|-------|------
 * b|  x      |0      |0      |bcd    |bcde   |bcdef
 *  |---------|-------|-------|-------|-------|------
 * c|  x      |x      |0      |0      |cde    |cdef
 *  |---------|-------|-------|-------|-------|------
 * d|  x      |x      |x      |0      |0      |def
 *  |---------|-------|-------|-------|-------|------
 * e|  x      |x      |x      |x      |0      |0
 *  |---------|-------|-------|-------|-------|------
 * f|  x      |x      |x      |x      |x      |0
 *
 * using the above method you will only be solving small problems to get the minimum
 * cost tesselation. this should also reduce the complexity of the problem.
 */

public class ExactMethod {

    //array list made to store accepted triangles
    private final ArrayList<Coordinate> tempList = new ArrayList<>();
    private Triangle t;
    Triangle[][] cTable;
    private int num;

    public ExactMethod() {
    }

    public ArrayList<Coordinate> getTempList() {
        return tempList;
    }

    // helper method to find the distance between 2 points.
    public int distance(int x, int y, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x - x2), 2) + Math.pow((y - y2), 2));
    }

    //helper method to find the weight of a triangle.
    public int weight(ArrayList<Coordinate> pointList, int i, int j, int k) {
        Coordinate pointA = pointList.get(i);
        Coordinate pointB = pointList.get(j);
        Coordinate pointC = pointList.get(k);

        makeTriangle(pointList, i, j, k);

        return distance(pointA.x, pointA.y, pointB.x, pointB.y)
                + distance(pointB.x, pointB.y, pointC.x, pointC.y)
                + distance(pointC.x, pointC.y, pointA.x, pointA.y);
    }

    public void makeTriangle(ArrayList<Coordinate> pointList, int i, int j, int k) {
        Coordinate a = pointList.get(i);
        Coordinate b = pointList.get(j);
        Coordinate c = pointList.get(k);

        CoordinateWithDistance lineA = new CoordinateWithDistance(a.x, a.y, b.x, b.y, 0);
        CoordinateWithDistance lineB = new CoordinateWithDistance(b.x, b.y, c.x, c.y, 0);
        CoordinateWithDistance lineC = new CoordinateWithDistance(c.x, c.y, a.x, a.y, 0);

        t = new Triangle(lineA, lineB, lineC, 0);
    }

    public Triangle getT() {
        return t;
    }

    public Triangle[][] getcTable() {
        return cTable;
    }

    public int startExact(ArrayList<Coordinate> pointList, int n) {

        this.num = n;
        //base case
        if (n <= 3) {
            return 0;
        }
        //this table will be to store the sub problem weights
        Pair[][] costTable = new Pair[n][n];
        cTable = new Triangle[n][n];

        for (int g = 0; g < n; g++) {

            for (int i = 0, j = g; j < n; i++, j++) {

                if (j < i + 2) {
                    continue;
                } else {
                    costTable[i][j].c = 10000000;

                    for (int k = i + 1; k < j; k++) {
                        int x = (costTable[i][k].c + costTable[k][j].c + weight(pointList, i, j, k));
                        if (x < costTable[i][j].c) {
                            costTable[i][j].c = x;
                            costTable[i][j].k = i;
                            cTable[i][j] = t;
                        }
                    }
                }
            }
        }
        System.out.println("are we getting here ?");
        drawTriangle(costTable,1,n);
        return costTable[0][n - 1].c;
    }

    public void drawTriangle(Pair[][] t, int i, int j){
        if(i != j){
            System.out.println("hello");
            System.out.println((i-1%num+1) + (t[i][j].k) + j);
            drawTriangle(t,i,t[i][j].k);
            drawTriangle(t,(t[i][j].k+1%num+1),j);
        }
    }

    private class Pair {
        int c;
        int k;

        public Pair(int c, int k) {
            this.c = c;
            this.k = k;
        }
    }
}
