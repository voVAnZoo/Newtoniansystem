import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

/**
 * Created by Vova on 22.01.2018.
 */
public class GrafoniumLaunch extends JComponent {
    JFrame wind;
    int scen;
    int mausX;
    int mausY;
    double k,l;

    public GrafoniumLaunch(JFrame a){
        super();
        init();
        scen = 1;
        wind = a;
        this.addMouseMotionListener(new MouseMotionListener(){

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mausX = e.getX();
                mausY = e.getY();
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    static Image im1;
    static Image im2;
    static Image im3;
    static Image im4;
    static Image im5;
    static Image im6;
    static Image im7;
    static Image im8;
    static Image im9;

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2=(Graphics2D)g;

        switch (scen){
            case 1:
                scen1(g2);
                break;
        }

        super.repaint();
    }

    public static void init(){
        try {
            im1 = ImageIO.read(new File("stars.png")); // задний фон
            im2 = ImageIO.read(new File("star_1.png")); // кусок звезды
            im3 = ImageIO.read(new File("star_2.png")); // это тоже кусок звезды
            im4 = ImageIO.read(new File("star_3.png")); // ещё кусок звезды
//            im5 = ImageIO.read(new File("menu_button.png")); // кнопка
            im6 = ImageIO.read(new File("menu_all_buttons.png")); // кнопки
            im7 = ImageIO.read(new File("choice1.png")); // кнопки
            im8 = ImageIO.read(new File("choice2.png")); // кнопки
            im9 = ImageIO.read(new File("choice3.png")); // кнопки

        }catch (Exception e){

        }
    }

    public void scen1(Graphics2D g2){
        k = (double)(wind.getWidth()-16) / (double)(im1.getWidth(null)) ;
        l = (double)(wind.getHeight()-48) / (double)(im1.getHeight(null)) ;
        g2.drawImage(im1,0,0,(int)(im1.getWidth(null)*k),(int)(im2.getHeight(null)*l),null);
        g2.drawImage(im2,0,0,(int)(im2.getWidth(null)*k),(int)(im2.getHeight(null)*l),null);
        g2.drawImage(im3,0,0,(int)(im3.getWidth(null)*k),(int)(im3.getHeight(null)*l),null);
        g2.drawImage(im4,0,0,(int)(im4.getWidth(null)*k),(int)(im2.getHeight(null)*l),null);
        g2.drawImage(im6,0,0,(int)(im6.getWidth(null)*k),(int)(im6.getHeight(null)*l),null);

    }
}
