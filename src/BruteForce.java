import java.awt.geom.Line2D;
import java.util.ArrayList;

public class BruteForce {
    ArrayList<CoordinateWithDistance> interiorLineList;
    int exteriorSides; //number of sides
    int interiorEdges;
    int catalina; //number of combinations
    ArrayList<Combo> arrayOfOptions;
    CoordinateWithDistance cd;
    int c;
    int n;
    int o;

    public BruteForce(ArrayList<CoordinateWithDistance> lineList, int sides) {
        this.interiorLineList = lineList;
        this.interiorEdges = lineList.size();
        exteriorSides = sides;
        CatalinaNumber num = new CatalinaNumber(sides);
        catalina = num.catalina;

        recursiveFindCombos(0);


    }

    public void recursiveFindCombos(int i){
        c = i;
        n = i+1;
        o = i+2;

        //recursive through points
        if (i != interiorEdges-1){
            cd = interiorLineList.get(i);
            twoLineCheck();

        }

    }

    public void twoLineCheck(){

        CoordinateWithDistance nd = interiorLineList.get(n);

        if (!Line2D.linesIntersect(cd.x, cd.y, cd.x2, cd.y2, nd.x, nd.y, nd.x2, nd.y2)){
            threeLineCheck(cd, nd, o);
        }

        if (n == interiorEdges-1){
            recursiveFindCombos(c++);
        }
        else {
            n++;
            twoLineCheck();
        }

    }


    public void threeLineCheck(CoordinateWithDistance cd, CoordinateWithDistance nd, int o){ //o = 2, n = 1
        CoordinateWithDistance od = interiorLineList.get(o);

        if (!Line2D.linesIntersect(cd.x, cd.y, cd.x2, cd.y2, od.x, od.y, od.x2, od.y2) ||
                !Line2D.linesIntersect(od.x, od.y, od.x2, od.y2, nd.x, nd.y, nd.x2, nd.y2)){

            Combo newCombo = new Combo(cd, nd, od, exteriorSides);
            if (!arrayOfOptions.contains(newCombo)){
                arrayOfOptions.add(newCombo);
            }
        }

        if (o == interiorEdges-1){
            n++;
            twoLineCheck();
        }
        else {
            o++;
            threeLineCheck(cd, nd, o);
        }
    }


}


class Combo{
    CoordinateWithDistance a;
    CoordinateWithDistance b;
    CoordinateWithDistance c;
    double sumOfDistances;
    int exteriorSides;

    public Combo(CoordinateWithDistance a, CoordinateWithDistance b, CoordinateWithDistance c, int exteriorSides) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.exteriorSides = exteriorSides-3;
        this.sumOfDistances = ((a.getDistance() + b.getDistance() + c.getDistance())/(exteriorSides));
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

    public double getTotal() {
        return sumOfDistances;
    }

    @Override
    public String toString() {
        return "Combo {" +
                " a = " + a +
                ", b = " + b +
                ", c = " + c +
                ", sumOfDistances=" + sumOfDistances + "}\n";
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Combo c = (Combo) object;
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
