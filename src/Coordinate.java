import java.util.Comparator;
//class to store the coordinate points for the polygon shapes

public class Coordinate{

        public int x;
        public int y;
        boolean visit;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
            this.visit = false;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "] ";
        }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Coordinate c = (Coordinate) object;
            if (this.x == (c.getX()) && this.y == (c.getY())  )
                     {
                result = true;
            }
        }
        return result;
    }
}


