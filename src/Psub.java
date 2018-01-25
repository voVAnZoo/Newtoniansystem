import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Vova on 22.01.2018.
 */
public class Psub extends Subject {

    public boolean isActiv;
    Image img;


    public Psub(double m) {
        super(m);
    }

    public Psub(double m, double x, double y, double speedX, double speedY, double accelX, double accelY, double phi) {
        super(m, x, y, speedX, speedY, accelX, accelY, phi);

        isActiv = true;
        try {
            img = ImageIO.read(new File("st.png"));
        }catch (Exception e) {

        }
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setColor(Color.ORANGE);

        AffineTransform affine = new AffineTransform();
        Image image = img;


        try {

            affine.rotate(Math.toRadians(this.phi), (int)(x*Data.camSize - Data.camX) +  (int)(width*Data.camSize)/ 2,
                    (int)(y*Data.camSize - Data.camY) + (int)(height*Data.camSize) / 2);
            g2.setTransform(affine);
            g2.drawImage(img,(int)(x*Data.camSize - Data.camX),(int)(y*Data.camSize - Data.camY),(int)(width*Data.camSize),(int)(height*Data.camSize),null);
        }catch (Exception e){

        }
        //g2.fillRect((int)(x*Data.camSize - Data.camX),(int)(y*Data.camSize - Data.camY),(int)(20*Data.camSize),(int)(20*Data.camSize));
    }

    @Override
    public void action(){
        if(isActiv) {
            if (Data.d) {
                phi = (phi+0.5+360)%360;
            }
            if (Data.a) {
                phi = (phi-0.5+360)%360;
            }
            if (Data.w) {
                speedY -= 0.01*Math.cos(Math.toRadians(this.phi));
                speedX += 0.01*Math.sin(Math.toRadians(this.phi));
            }
            if (Data.s) {
                speedY += 0.01*Math.cos(Math.toRadians(this.phi));
                speedX -= 0.01*Math.sin(Math.toRadians(this.phi));
            }


            x = (x + speedX - width + Data.sSize.width) % (Data.sSize.width - width);
            y = (y + speedY - height + Data.sSize.height) % (Data.sSize.height - height);
        }
    }
}
