import java.awt.*;

/**
 * Created by Vova on 22.01.2018.
 */
public class Subject {

    double m;
    double x;
    double y;
    double speedX;
    double speedY;
    double accelX;
    double accelY;
    double phi;

    int width = 60;
    int height = 60;

    public void setM(double m) {
        this.m = m;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setAccelX(double accelX) {
        this.accelX = accelX;
    }

    public void setAccelY(double accelY) {
        this.accelY = accelY;
    }

    public void setPhi(double phi) {
        this.phi = phi;
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

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getAccelX() {
        return accelX;
    }

    public double getAccelY() {
        return accelY;
    }

    public double getPhi() {
        return phi;
    }

    public Subject(double m){
        this.m = m;
        this.x = 0;
        this.y = 0;
        this.speedX = 0;
        this.speedY = 0;
        this.accelX = 0;
        this.speedY = 0;
        this.phi = 0;
    }

    public Subject(double m, double x, double y, double speedX, double speedY, double accelX, double accelY, double phi){
        this.m = m;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.accelX = accelX;
        this.accelY = accelY;
        this.phi = phi;
    }

    public void draw(Graphics2D g2){

    }

    public void action(){

    }

}
