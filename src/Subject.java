import java.awt.*;

/**
 * Created by Vova on 22.01.2018.
 */
public class Subject {

    double m;
    double x;
    double y;
    double phi;

    int width;
    int height;

    public void setM(double m) {
        this.m = m;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double getM() {
        return m;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public Subject(double m){
        this.m = m;
        this.x = 0;
        this.y = 0;
    }

    public Subject(double m, double x, double y){
        this.m = m;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2){

    }

    public void action(){

    }

}
