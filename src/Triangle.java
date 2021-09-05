import java.util.ArrayList;

class Triangle {
    CoordinateWithDistance a;
    CoordinateWithDistance b;
    CoordinateWithDistance c;
    double hypothenus;
    int exteriorSides;

    public Triangle(CoordinateWithDistance a, CoordinateWithDistance b, CoordinateWithDistance c, double v) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.hypothenus = c.distance;
    }

    public CoordinateWithDistance getA() {
        return a;
    }

    public CoordinateWithDistance getB() {
        return b;
    }

    public CoordinateWithDistance getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a= " + a +
                ", b= " + b +
                ", c= " + c +
                "}\n";
    }

    //    @Override
//    public String toString() {
//        return "Combo {" +
//                " a = " + a +
//                ", b = " + b +
//                ", c = " + c +
//
//    }
//
    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Triangle c = (Triangle) object;
            if ((this.a == (c.getA()) && this.b == (c.getB()) && this.c == (c.getC())) ||
                    (this.a == (c.getB()) && this.b == (c.getC()) && this.c == (c.getA())) ||
                    (this.a == (c.getC()) && this.b == (c.getA()) && this.c == (c.getB()))
            )
            {
                result = true;
            }
        }
        return result;
    }
}
