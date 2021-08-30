public class CoordinateWithDistance extends Coordinate implements Comparable<CoordinateWithDistance>{
    protected double distance;
    int x2;
    int y2;

    public CoordinateWithDistance(int x, int y, int x_2, int y_2, double d) {
        super(x, y);
        distance = d;
        x2= x_2;
        y2 = y_2;

    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return "([" + x + ", " + y + "], " + "[" + x2 + ", " + y2 + "] : " +distance +"), ";
    }

    @Override
    public int compareTo(CoordinateWithDistance o) {
        if (distance > o.distance){
            return 1;
        }
        else if(distance == o.distance){
            return 0;
        }
        return -1;
    }
}
