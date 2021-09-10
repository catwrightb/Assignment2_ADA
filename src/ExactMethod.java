import java.util.ArrayList;
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
    ArrayList<Triangle> triangleTable;

    public ExactMethod() {
    }

    public ArrayList<Coordinate> getTempList() {
        return tempList;
    }

    // helper method to find the distance between 2 points.
    public double distance(int x, int y, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x - x2), 2) + Math.pow((y - y2), 2));
    }

    //helper method to find the weight of a triangle.
    public double weight(ArrayList<Coordinate> pointList, int i, int j, int k) {
        Coordinate pointA = pointList.get(i);
        Coordinate pointB = pointList.get(j);
        Coordinate pointC = pointList.get(k);

        return (int) distance(pointA.x, pointA.y, pointB.x, pointB.y)
                + distance(pointB.x, pointB.y, pointC.x, pointC.y)
                + distance(pointC.x, pointC.y, pointA.x, pointA.y);
    }

    public ArrayList<Triangle> getTriTable() {
        return triangleTable;
    }

    public double startExact(ArrayList<Coordinate> pointList, int n) {
        //this table will be to store the sub problem weights
        double[][] ctable = new double[n][n];
        //this table is for storing the index needed
        int[][] iTable = new int[n][n];

        for (int l = 0; l < n; l++) {
            int j = l;
            for (int i = 0; j < n; i++, j++) {
                //adding a 0 to all points that are not above 3 sides so everything that isn't a triangle is 0
                if (j < i + 2) {
                    ctable[i][j] = 0;
                } else {
                    /*making all the other points in the cost Table to be infinity or as many numbers as im allowed here
                    * so that when checked in the below if statement only the triangles can be chosen as the other indexes
                    * will always be smaller than x.
                    */
                    ctable[i][j] = 1000000000;
                    for (int k = i + 1; k <= j - 1; k++) {
                        // creates the cost x as the 2 previous sub-problem costs + the new sub-problem
                        double x = ctable[i][k] + ctable[k][j] + weight(pointList, i, k, j);
                        //checks of infinity is smaller that ctable[i][j]
                        if (x < ctable[i][j]) {
                            ctable[i][j] = x;
                            iTable[i][j] = k;
                        }
                    }
                }
            }
        }
//        drawTriangle(iTable, 0, n - 1, n);

        //prints out the index table was just for testing
        for (int[] r : iTable) {
            System.out.println(".");
            for (int d : r) {
                System.out.print(d + ", ");
            }
        }
        System.out.println("\n");

        //returns the minimum cost as a double for the final triangle.
        return ctable[0][n - 1];
    }

    /*
    * this method will hopefully give back optimal triangles using preorder traversal ie using k as the parent
    * then finding the children triangles from each sub-problem to get the optimal triangles.
     */
    public void drawTriangle(int[][] s, int i, int j, int n) {
        if (i != j) {
            System.out.println(i + " " + s[i][j] + " " + j);
            drawTriangle(s, i, s[i][j], n);
            drawTriangle(s, (s[i][j] + 1) % (n), j, n);
        }
    }

}
