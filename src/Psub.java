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
    public double accelRotation = 0;
    public double speedRotation;

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
                speedRotation = 0.5;
                break;
            case 2:
                s1 = "cruiser/";
                speedRotation = 0;
                break;
            case 3:
                s1 = "cruiser/";
                //s1 = "battleship/";
                break;
        }

        isActiv = true;
        try {
            img.add(ImageIO.read(new File(s1 + "sh0.png")));
            img.add(ImageIO.read(new File(s1 + "sh1.png")));
            img.add(ImageIO.read(new File(s1 + "sh2.png")));
            img.add(ImageIO.read(new File(s1 + "sh3.png")));
            img.add(ImageIO.read(new File(s1 + "sh4.png")));
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
                speedRotation = 0.5;
                break;
            case 2:
                s1 = "cruiser/";
                speedRotation = 0;
                break;
            case 3:
                s1 = "cruiser/";
                //s1 = "battleship/";
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
        width = img.get(0).getWidth(null);
        height = img.get(0).getHeight(null);
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
            speedY += accel * 0.99 * Math.cos(Math.toRadians(this.phi));
            speedX -= accel * 0.99 * Math.sin(Math.toRadians(this.phi));
        }
        if (Data.q) {
            speedY -= 4 * accel * Math.cos(Math.toRadians(this.phi));
            speedX += 4 * accel * Math.sin(Math.toRadians(this.phi));
        }
        if (Data.e) {
            speedY += 4 * accel * Math.cos(Math.toRadians(this.phi));
            speedX -= 4 * accel * Math.sin(Math.toRadians(this.phi));
        }

        x = (x + speedX - width + Data.sSize.width) % (Data.sSize.width - width);
        y = (y + speedY - height + Data.sSize.height) % (Data.sSize.height - height);
    }

    public void actionCruiser() {
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
            y -= 0.5 * Math.sin(Math.toRadians(this.phi));
            x -= 0.5 * Math.cos(Math.toRadians(this.phi));
        }

        if (Data.e) {
            y += 0.5 * Math.sin(Math.toRadians(this.phi));
            x += 0.5 * Math.cos(Math.toRadians(this.phi));
        }

        x = (x + speedX - width + Data.sSize.width) % (Data.sSize.width - width);
        y = (y + speedY - height + Data.sSize.height) % (Data.sSize.height - height);
        speedRotation += (accelRotation - Math.signum(speedRotation) * (speedRotation * speedRotation) * 0.005);

        if (Math.abs(speedRotation) > 0.008) {
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

            if (Data.w) {
                /*double alpha = 0.5;
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha);
                g2.setComposite(ac);*/
                g2.drawImage(img.get(2),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (width * Data.camSize),
                        (int) (height * Data.camSize),
                        null);
            } else {
                if (Data.a || Data.s || Data.d) {

                    g2.drawImage(img.get(1),
                            (int) (x * Data.camSize - Data.camX),
                            (int) (y * Data.camSize - Data.camY),
                            (int) (width * Data.camSize),
                            (int) (height * Data.camSize),
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
        } catch (Exception e) {
        }
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

            if (Data.w) {
                g2.drawImage(img.get(1),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(1).getWidth(null) * Data.camSize),
                        (int) (img.get(1).getHeight(null) * Data.camSize),
                        null);
            }
            if(Data.a){
                g2.drawImage(img.get(3),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(3).getWidth(null) * Data.camSize),
                        (int) (img.get(3).getHeight(null) * Data.camSize),
                        null);
            }
            if(Data.s){
                g2.drawImage(img.get(4),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(4).getWidth(null) * Data.camSize),
                        (int) (img.get(4).getHeight(null) * Data.camSize),
                        null);
            }
            if(Data.d){
                g2.drawImage(img.get(2),
                        (int) (x * Data.camSize - Data.camX),
                        (int) (y * Data.camSize - Data.camY),
                        (int) (img.get(2).getWidth(null) * Data.camSize),
                        (int) (img.get(2).getHeight(null) * Data.camSize),
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


}
