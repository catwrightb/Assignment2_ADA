import java.util.Comparator;
//class to store the coordinate points for the polygon shapes

public class Coordinate{

        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "] ";
        }

}


