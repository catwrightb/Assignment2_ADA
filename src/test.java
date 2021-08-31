import java.util.ArrayList;

public class test {

    static int result=1;
    static int end;

    static void factorial( int n ) {
        if (n != end) {
            result *= n; // recursive call
            System.out.println(n);
            System.out.println(result);
            factorial(n-1) ;
        }

    }

    public static void main(String[] args) {

        ArrayList<CoordinateWithDistance> test = new ArrayList<>();

        test.add(new CoordinateWithDistance(3,3,4,6,345.0));
        test.add(new CoordinateWithDistance(5,1,4,6,345.0));
        test.add(new CoordinateWithDistance(3,2,4,6,345.0));
        test.add(new CoordinateWithDistance(4,3,4,6,345.0));
        Coordinate ct = new CoordinateWithDistance(3,2,4,6,345.0);

        if (test.contains((ct))){
            System.out.println("true");
        }
        else
            System.out.println("false");

//        int number = 4;
//        end = number-2;
//        factorial(number);
//        System.out.println(number + " factorial = " + result);

//////        double r = 40.0;
//////        double angle1 = Math.random()* (2 * Math.PI);
//////        double angle2 = Math.random()* (2 * Math.PI);
//////        double angle3 = Math.random()* (2 * Math.PI);
////
////        double x_1 = r * Math.cos(angle1);
////        double y_1 = r * Math.sin(angle1);
////        double x_2 = r * Math.cos(angle2);
////        double y_2 = r * Math.sin(angle2);
////        double x_3 = r * Math.cos(angle3);
////        double y_3 = r * Math.sin(angle3);
//
//        ArrayList<Coordinate> angles = new ArrayList<>();
//        ArrayList<Double> total = new ArrayList<>();
//
//
//        angles.add(new Coordinate(1,4));
//        angles.add(new Coordinate(2,5));
//        angles.add(new Coordinate(4,4));
//        angles.add(new Coordinate(4,1));
//        angles.add(new Coordinate(1,1));
//        int n= angles.size();
//
//        for(int i = 0; i < n-2; i++){
//            int last = (i - 1 + n) % n;
//            int next = (i + 1) % n;
//            double x1 = angles.get(i).x;
//            double y1 = angles.get(i).y;
//            double x2 = angles.get(next).x ;
//            double y2 = angles.get(next).y;
//            double x3 = angles.get(last).x ;
//            double y3 = angles.get(last).y ;
//
//            total.add(triangle(x1,y1,x2,y2,x3,y3, i));
//
//        }
//
//        double sum = 0;
//        for (int i = 0; i < total.size(); i++) {
//            sum += total.get(i);
//        }
//
//        System.out.println(sum);
//        // two coordinates x and y
////        double a1 = 0.0;
////        double b1 = 0.0;
////        double a2 = 6.0;
////        double b2 = 0.0;
////        double a3 = 0.0;
////        double b3 = 8.0;
////
////        double x1 = a1 - a3;
////        double y1 = b1 - b3;
////        double x2 = a2 - a1;
////        double y2 = b2  - b1;
////
////        double a = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2)); // distance from 1 to 2
////        double b = Math.sqrt(Math.pow(x_3 - x_2, 2) + Math.pow(y_3 - y_2, 2)); // distance from 2 to 3
////        double c = Math.sqrt(Math.pow(x_1 - x_3, 2) + Math.pow(y_1 - y_3, 2)); // distance from 3 to 1
////        //Get angles ***
////        double triangleAngle1 = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b));
////        double triangleAngle2 = Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * c * b));
////        double triangleAngle3 = Math.acos((Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2)) / (2 * a * c));
////
////        // get angle θ
////        double theta = Math.atan2(y1, x1);
////        System.out.println(theta);
////        // convert into the degree
////        System.out.println("degrees of theta: "+Math.toDegrees(theta));
//
//
   }
//
//    public static Double triangle(double x_1, double y_1, double x_2,  double y_2, double x_3,
//            double y_3 , int i){
//
//        System.out.println(i + " round");
//
//        System.out.println("The coordinates of the three points are: "+
//                ( + x_1 + ", " + y_1 )+
//                (+ x_2 + ", " + y_2)+
//                (+ x_3 + ", " + y_3));
//        //Get length of each side
//        double a = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2)); // distance from 1 to 2
//        double b = Math.sqrt(Math.pow(x_3 - x_2, 2) + Math.pow(y_3 - y_2, 2)); // distance from 2 to 3
//        double c = Math.sqrt(Math.pow(x_1 - x_3, 2) + Math.pow(y_1 - y_3, 2)); // distance from 3 to 1
//        //Get angles ***
//        double triangleAngle1 = Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)));
//        double triangleAngle2 = Math.toDegrees(Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * c * b)));
//        double triangleAngle3 = Math.toDegrees(Math.acos((Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2)) / (2 * a * c)));
//
//        System.out.println("The three angles are " + triangleAngle1 + " " +
//                triangleAngle2 + " " + triangleAngle3);
//        System.out.println(triangleAngle1 + triangleAngle2 + triangleAngle3);
//
//        return triangleAngle1 + triangleAngle2 + triangleAngle3;
//
//
//
//    }
}
