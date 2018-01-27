import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Vova on 22.01.2018.
 */
public class Psub extends Subject {

    public boolean isActiv;
    public int type = 0;
    public double accel = 0.01;
    public double speedRotation = 0.5;

    /*
    * 1 - Истребитель
    * 2 - Крейсер
    * 3 - Линкор
    * */
    java.util.List<Image> img = new ArrayList<Image>();


    public Psub(double m, double x, double y, double speedX, double speedY, double accelX, double accelY, double phi, int type) {
        super(m, x, y, speedX, speedY, accelX, accelY, phi);
        this.type = type;
        String s1 = "";
        switch (type) {
            case 1:
                s1 = "fighter/";
                break;

            case 2:
                s1 = "cruiser/";
                break;

            case 3:
                s1 = "battleship/";
                break;
        }

        isActiv = true;
        try {
            img.add(ImageIO.read(new File(s1 + "sh0.png")));
            img.add(ImageIO.read(new File(s1 + "sh1.png")));
            img.add(ImageIO.read(new File(s1 + "sh2.png")));
        } catch (Exception e) {

        }

        width = img.get(0).getWidth(null);
        height = img.get(0).getHeight(null);
    }

    public Psub(double m, int type) {
        super(m);
        this.type = type;
        String s1 = "";
        switch (type) {
            case 1:
                s1 = "fighter/";
                break;

            case 2:
                s1 = "cruiser/";
                break;

            case 3:
                s1 = "battleship/";
                break;
        }
        isActiv = true;
        try {
            img.add(ImageIO.read(new File(s1 + "sh0.png")));
            img.add(ImageIO.read(new File(s1 + "sh1.png")));
            img.add(ImageIO.read(new File(s1 + "sh2.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        width = 120;
        height = 120;
    }

    @Override
    public void draw(Graphics2D g2) {
        AffineTransform affine = new AffineTransform();
        switch (type) {
            case 1:
                try {
                    affine.rotate(Math.toRadians(this.phi), (int) (x * Data.camSize - Data.camX)
                            + (int) (width * Data.camSize) / 2, (int) (y * Data.camSize - Data.camY)
                            + 2 * (int) (height * Data.camSize) / 3);

                    g2.setTransform(affine);

                    if (Data.w) {
                        g2.drawImage(img.get(2), (int) (x * Data.camSize - Data.camX), (int) (y * Data.camSize - Data.camY),
                                (int) (width * Data.camSize), (int) (height * Data.camSize), null);
                    } else {
                        if (Data.a || Data.s || Data.d) {
                            g2.drawImage(img.get(1), (int) (x * Data.camSize - Data.camX), (int) (y * Data.camSize - Data.camY),
                                    (int) (width * Data.camSize), (int) (height * Data.camSize), null);
                        } else {
                            g2.drawImage(img.get(0), (int) (x * Data.camSize - Data.camX), (int) (y * Data.camSize - Data.camY),
                                    (int) (width * Data.camSize), (int) (height * Data.camSize), null);
                        }
                    }
                } catch (Exception e) {}
                //g2.fillRect((int)(x*Data.camSize - Data.camX),(int)(y*Data.camSize - Data.camY),(int)(20*Data.camSize),(int)(20*Data.camSize));
                break;

            case 2:
                try {
                    affine.rotate(Math.toRadians(this.phi),
                            (int) (x * Data.camSize - Data.camX) + (int) (width * Data.camSize) / 2,
                            (int) (y * Data.camSize - Data.camY) + (int) (height * Data.camSize) / 2);

                    g2.setTransform(affine);

                    if (Data.w) {
                        g2.drawImage(img.get(0),
                                (int) (x * Data.camSize - Data.camX),
                                (int) (y * Data.camSize - Data.camY),
                                (int) (width * Data.camSize),
                                (int) (height * Data.camSize),
                                null);

                        g2.drawImage(img.get(2),
                                (int) (x * Data.camSize - Data.camX),
                                (int) (y * Data.camSize - Data.camY),
                                (int) (img.get(2).getWidth(null) * Data.camSize),
                                (int) (img.get(2).getHeight(null) * Data.camSize),
                                null);
                    } else {
                        if (Data.a || Data.s || Data.d) {

                            g2.drawImage(img.get(0),
                                    (int) (x * Data.camSize - Data.camX),
                                    (int) (y * Data.camSize - Data.camY),
                                    (int) (width * Data.camSize),
                                    (int) (height * Data.camSize),
                                    null);

                            g2.drawImage(img.get(1),
                                    (int) (x * Data.camSize - Data.camX),
                                    (int) (y * Data.camSize - Data.camY),
                                    (int) (img.get(1).getWidth(null) * Data.camSize),
                                    (int) (img.get(1).getHeight(null) * Data.camSize),
                                    null);

                        } else {
                            g2.drawImage(img.get(0),
                                    (int) (x * Data.camSize - Data.camX),
                                    (int) (y * Data.camSize - Data.camY),
                                    (int) (width * Data.camSize),
                                    (int) (height * Data.camSize),
                                    null);
                        }
                    }
                } catch (Exception e) {}
                break;

            case 3:
                break;
        }


    }

    @Override
    public void action() {
        if (isActiv) {
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
                speedY += accel * Math.cos(Math.toRadians(this.phi));
                speedX -= accel * Math.sin(Math.toRadians(this.phi));
            }
            if (Data.q) {


            }
            if (Data.e) {


            }


            x = (x + speedX - width + Data.sSize.width) % (Data.sSize.width - width);
            y = (y + speedY - height + Data.sSize.height) % (Data.sSize.height - height);
        }
    }
}
