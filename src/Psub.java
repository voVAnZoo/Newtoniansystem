import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Vova on 22.01.2018.
 */
public class Psub extends Subject {

    boolean isActiv = false;
    int type = 0;
    double accel;
    double accelRotation;
    double speedRotation;
    double speedX;
    double speedY;
    double muRotation;
    double mu;
    double backAccel;
    double nitro;
    double minSpeedRotation;
    double shoot;
    double minAccel;
    double maxAccel;
    double sideAccel;

    /*
    * 1 - Истребитель
    * 2 - Крейсер
    * 3 - Линкор
    * */

    java.util.List<Image> img = new ArrayList<Image>();

    public Psub(double m, double x, double y, int type) {
        super(m, x, y);
        this.type = type;

        switch (type) {
            case 1:
                init("fighter");
                break;
            case 2:
                init("cruiser");
                break;
            case 3:
                init("battleship");
                break;
            default:
                this.type = 1;
                init("fighter");
                break;
        }
    }

    public Psub(double m, int type) {
        super(m);
        this.type = type;
        switch (type) {
            case 1:
                init("fighter");
                speedRotation = 0.5;
                break;
            case 2:
                init("cruiser");
                speedRotation = 0;
                break;
            case 3:
                init("battleship");
                break;
            default:
                this.type = 1;
                init("fighter");
                break;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        switch (type) {
            case 1:
                drawFighter(g2);
                break;
            case 2:
                drawCruiser(g2);
                break;
            case 3:
                drawBattleship(g2);
                break;
        }
    }

    @Override
    public void action() {
        if (isActiv) {
            switch (type) {
                case 1:
                    actionFighter();
                    break;

                case 2:
                    actionCruiser();
                    break;

                case 3:
                    actionBattleship();
                    break;
            }
        }
    }

    public void actionFighter() {
        if (Data.d) {
            phi = (phi + speedRotation + 360) % 360;
        }
        if (Data.a) {
            phi = (phi - speedRotation + 360) % 360;
        }
        if (Data.w) {
            speedY -= accel * Math.cos(Math.toRadians(this.phi));
            speedX += accel * Math.sin(Math.toRadians(this.phi));
        }
        if (Data.s) {
            speedY += accel * backAccel * Math.cos(Math.toRadians(this.phi));
            speedX -= accel * backAccel * Math.sin(Math.toRadians(this.phi));
        }
        if (Data.q) {
            speedY -= nitro * accel * Math.cos(Math.toRadians(this.phi));
            speedX += nitro * accel * Math.sin(Math.toRadians(this.phi));
        }
        if (Data.e) {
            speedY += nitro * accel * Math.cos(Math.toRadians(this.phi));
            speedX -= nitro * accel * Math.sin(Math.toRadians(this.phi));
        }

        speedX -= mu * speedX * Math.sqrt(Math.pow(speedY,2) + Math.pow(speedX,2));
        speedY -= mu * speedY * Math.sqrt(Math.pow(speedY,2) + Math.pow(speedX,2));

        x = (x + speedX - width + Data.sSize.width) % (Data.sSize.width - width);
        y = (y + speedY - height + Data.sSize.height) % (Data.sSize.height - height);
    }

    public void actionCruiser() {

        if(!Data.a){
            if(!Data.d){
                speedRotation -= Math.signum(speedRotation) * Math.pow(speedRotation, 2) * muRotation;
            }else {
                speedRotation += (accelRotation - Math.signum(speedRotation) * Math.pow(speedRotation, 2) * muRotation);
            }
        }else {
            if(!Data.d){
                speedRotation += (- accelRotation - Math.signum(speedRotation) * Math.pow(speedRotation, 2) * muRotation);
            }else {
                speedRotation -= Math.signum(speedRotation) * Math.pow(speedRotation, 2) * muRotation;
            }
        }

        if (Data.w) {
            if (accel + shoot > maxAccel) {
                accel = maxAccel;
            } else {
                accel += shoot;
            }
        }

        if (Data.s) {
            if (accel - shoot < minAccel) {
                accel = minAccel;
            } else {
                accel -= shoot;
            }
        }

        if (Data.q) {
            speedY -= sideAccel * Math.sin(Math.toRadians(this.phi));
            speedX -= sideAccel * Math.cos(Math.toRadians(this.phi));
        }

        if (Data.e) {
            speedY += sideAccel * Math.sin(Math.toRadians(this.phi));
            speedX += sideAccel * Math.cos(Math.toRadians(this.phi));
        }

        /*speedY -= accel * Math.cos(Math.toRadians(this.phi)) - Math.signum(speedY) * (Math.pow(speedX,2) + Math.pow(speedY,2)) * mu * Math.cos(Math.toRadians(this.phi));
        speedX += accel * Math.sin(Math.toRadians(this.phi)) - Math.signum(speedX) * (Math.pow(speedX,2) + Math.pow(speedY,2)) * mu * Math.sin(Math.toRadians(this.phi));
*/
        speedX -= mu * speedX * Math.sqrt(Math.pow(speedY,2) + Math.pow(speedX,2));
        speedY -= mu * speedY * Math.sqrt(Math.pow(speedY,2) + Math.pow(speedX,2));

        speedX += accel * Math.sin(Math.toRadians(this.phi));
        speedY -= accel * Math.cos(Math.toRadians(this.phi));

        x = (x + speedX - width + Data.sSize.width) % (Data.sSize.width - width);
        y = (y + speedY - height + Data.sSize.height) % (Data.sSize.height - height);

        if (Math.abs(speedRotation) > minSpeedRotation) {
            phi = (phi + speedRotation + 360) % 360;
        }
    }

    public void actionBattleship() {
        if (Data.d) {
            if (accelRotation == -0.001) {
                accelRotation = 0;
            } else {
                accelRotation = 0.001;
            }
        } else {
            if (accelRotation != -0.001) {
                accelRotation = 0;
            }
        }

        if (Data.a) {
            if (accelRotation == 0.001) {
                accelRotation = 0;
            } else {
                accelRotation = -0.001;
            }
        } else {
            if (accelRotation != 0.001) {
                accelRotation = 0;
            }
        }

        if (Data.w) {
            if (Data.shift) {
                if (accel + 0.005 > 0.1) {
                    accel = 0.1;
                } else {
                    accel += 0.005;
                }
            } else {
                speedY -= accel * Math.cos(Math.toRadians(this.phi));
                speedX += accel * Math.sin(Math.toRadians(this.phi));
            }
        }

        if (Data.s) {
            if (Data.shift) {
                if (accel - 0.005 < 0) {
                    accel = 0;
                } else {
                    accel -= 0.005;
                }
            } else {
                speedY += accel * 0.99 * Math.cos(Math.toRadians(this.phi));
                speedX -= accel * 0.99 * Math.sin(Math.toRadians(this.phi));
            }
        }

        if (Data.q) {
            speedY -= 0.08 * accel * Math.sin(Math.toRadians(this.phi));
            speedX -= 0.08 * accel * Math.cos(Math.toRadians(this.phi));
        }

        if (Data.e) {
            speedY += 0.08 * accel * Math.sin(Math.toRadians(this.phi));
            speedX += 0.08 * accel * Math.cos(Math.toRadians(this.phi));
        }

        x = (x + speedX - width + Data.sSize.width) % (Data.sSize.width - width);
        y = (y + speedY - height + Data.sSize.height) % (Data.sSize.height - height);
        speedRotation += (accelRotation - Math.signum(speedRotation) * (speedRotation * speedRotation) * 0.005);

        if (Math.abs(speedRotation) > 0.008) {
            phi = (phi + speedRotation + 360) % 360;
        }
    }

    public void drawFighter(Graphics2D g2) {
        AffineTransform affine = new AffineTransform();
        try {
            affine.rotate(Math.toRadians(this.phi),
                    (int) (x * Data.camSize - Data.camX) + (int) (width * Data.camSize) / 2,
                    (int) (y * Data.camSize - Data.camY) + 2 * (int) (height * Data.camSize) / 3);
            g2.setTransform(affine);

            g2.drawImage(img.get(0),
                    (int) (x * Data.camSize - Data.camX),
                    (int) (y * Data.camSize - Data.camY),
                    (int) (width * Data.camSize),
                    (int) (height * Data.camSize),
                    null);

            /*double alpha = 0.5;
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha);
                g2.setComposite(ac);*/

            if (Data.w||Data.q) {
                g2.drawImage(img.get(1),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(1).getWidth(null) * Data.camSize),
                        (int) (img.get(1).getHeight(null) * Data.camSize),
                        null);
            }

            if(Data.a){
                g2.drawImage(img.get(2),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(2).getWidth(null) * Data.camSize),
                        (int) (img.get(2).getHeight(null) * Data.camSize),
                        null);
            }

            if(Data.d){
                g2.drawImage(img.get(3),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(3).getWidth(null) * Data.camSize),
                        (int) (img.get(3).getHeight(null) * Data.camSize),
                        null);
            }

            if(Data.s||Data.e){
                g2.drawImage(img.get(3),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(3).getWidth(null) * Data.camSize),
                        (int) (img.get(3).getHeight(null) * Data.camSize),
                        null);

                g2.drawImage(img.get(2),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(2).getWidth(null) * Data.camSize),
                        (int) (img.get(2).getHeight(null) * Data.camSize),
                        null);
            }


        } catch (Exception e) {}

        //g2.fillRect((int)(x*Data.camSize - Data.camX),(int)(y*Data.camSize - Data.camY),(int)(20*Data.camSize),(int)(20*Data.camSize));
    }

    public void drawCruiser(Graphics2D g2) {
        AffineTransform affine = new AffineTransform();
        try {
            affine.rotate(Math.toRadians(this.phi),
                    (int) (x * Data.camSize - Data.camX) + (int) (width * Data.camSize) / 2,
                    (int) (y * Data.camSize - Data.camY) + (int) (height * Data.camSize) / 2);

            g2.setTransform(affine);

            g2.drawImage(img.get(0),
                    (int) (x * Data.camSize - Data.camX),
                    (int) (y * Data.camSize - Data.camY),
                    (int) (width * Data.camSize),
                    (int) (height * Data.camSize),
                    null);

            if(accel > 0){
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (accel/maxAccel));
                g2.setComposite(ac);
                g2.drawImage(img.get(1),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(1).getWidth(null) * Data.camSize),
                        (int) (img.get(1).getHeight(null) * Data.camSize),
                        null);

            }else{
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (accel/minAccel));
                g2.setComposite(ac);
                g2.drawImage(img.get(4),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(4).getWidth(null) * Data.camSize),
                        (int) (img.get(4).getHeight(null) * Data.camSize),
                        null);

            }
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
            g2.setComposite(ac);

            /*if (Data.w) {
                g2.drawImage(img.get(1),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(1).getWidth(null) * Data.camSize),
                        (int) (img.get(1).getHeight(null) * Data.camSize),
                        null);
            }*/
            if(Data.a){
                g2.drawImage(img.get(3),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(3).getWidth(null) * Data.camSize),
                        (int) (img.get(3).getHeight(null) * Data.camSize),
                        null);
            }
            /*if(Data.s){
                g2.drawImage(img.get(4),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(4).getWidth(null) * Data.camSize),
                        (int) (img.get(4).getHeight(null) * Data.camSize),
                        null);
            }*/
            if(Data.d){
                g2.drawImage(img.get(2),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(2).getWidth(null) * Data.camSize),
                        (int) (img.get(2).getHeight(null) * Data.camSize),
                        null);
            }
            if(Data.q){
                g2.drawImage(img.get(6),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(6).getWidth(null) * Data.camSize),
                        (int) (img.get(6).getHeight(null) * Data.camSize),
                        null);
            }
            if(Data.e){
                g2.drawImage(img.get(5),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(5).getWidth(null) * Data.camSize),
                        (int) (img.get(5).getHeight(null) * Data.camSize),
                        null);
            }

        } catch (Exception e) {
        }
    }

    public void drawBattleship(Graphics2D g2) {
        AffineTransform affine = new AffineTransform();
        try {
            affine.rotate(Math.toRadians(this.phi),
                    (int) (x * Data.camSize - Data.camX) + (int) (width * Data.camSize) / 2,
                    (int) (y * Data.camSize - Data.camY) + (int) (height * Data.camSize) / 2);

            g2.setTransform(affine);

            g2.drawImage(img.get(0),
                    (int) (x * Data.camSize - Data.camX),
                    (int) (y * Data.camSize - Data.camY),
                    (int) (width * Data.camSize),
                    (int) (height * Data.camSize),
                    null);

            if (Data.w) {
                g2.drawImage(img.get(2),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(2).getWidth(null) * Data.camSize),
                        (int) (img.get(2).getHeight(null) * Data.camSize),
                        null);
            } else {
                if (Data.a || Data.s || Data.d) {
                    g2.drawImage(img.get(1),
                            (int) (x * Data.camSize - Data.camX),
                            (int) (y * Data.camSize - Data.camY),
                            (int) (img.get(1).getWidth(null) * Data.camSize),
                            (int) (img.get(1).getHeight(null) * Data.camSize),
                            null);
                }
            }
        } catch (Exception e) {
        }
    }

    public void init(String s1){
        try {
            File s[] = new File(s1 + "/").listFiles();
            for (File value : s) {
                img.add(ImageIO.read(value));
            }
        } catch (Exception e) {}

        width = img.get(0).getWidth(null);
        height = img.get(0).getHeight(null);

        config(s1);
    }

    public void config(String s1){
        try {
            Scanner cfgIn = new Scanner(new File("cfg/" + s1 + ".txt"));
            String s ;

            s = cfgIn.nextLine();
            accel = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            accelRotation = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            speedRotation = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            speedX = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            speedY = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            muRotation = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            mu = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            backAccel = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            nitro = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            minSpeedRotation = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            shoot = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            minAccel = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            maxAccel = Double.parseDouble(s.substring(0,s.indexOf('/')));

            s = cfgIn.nextLine();
            sideAccel = Double.parseDouble(s.substring(0,s.indexOf('/')));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void intefrace(){

    }

}
