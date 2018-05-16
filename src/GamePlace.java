import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vova on 22.01.2018.
 */
public class GamePlace {

    Image img, im1, im2, im3, im4, im5, im6, im7;
    public GamePlace(){
        try {
            img = ImageIO.read(new File("res/maxresdefault.png"));
            im1 = ImageIO.read(new File("res/button.png"));
            im2 = ImageIO.read(new File("res/choice.png"));
            im3 = ImageIO.read(new File("res/forvard_speed_blue.png"));
            im4 = ImageIO.read(new File("res/grid_speed.png"));
            im5 = ImageIO.read(new File("res/reverse_speed_red.png"));
            im6 = ImageIO.read(new File("res/maxresdefault.png"));
            im7 = ImageIO.read(new File("res/maxresdefault.png"));
        }catch (Exception e) {
            System.out.println("Траблы с текстурками");
        }
    }
    List<Subject> allsub = new ArrayList<Subject>();
    List<Psub> allplayer = new ArrayList<Psub>();

    public void draw(Graphics2D g2){

        g2.drawImage(img, (int)(-Data.camX), (int)(-Data.camY),
                (int) (Data.sSize.width * Data.camSize), (int) (Data.sSize.height * Data.camSize),null);

        for(int i = 0;i < allsub.size();i++){
            allsub.get(i).draw(g2);
        }

        for(int i = 0;i < allplayer.size();i++){
            allplayer.get(i).draw(g2);
        }
    }

    public void action() {

        for(int i = 0;i < allsub.size();i++){
            allsub.get(i).action();
        }

        for(int i = 0;i < allplayer.size();i++){
            allplayer.get(i).action();
        }
    }

    public void add(Subject s){
        allsub.add(s);
    }

    public void add(Psub s){
        allplayer.add(s);
    }

    public void interfce(Graphics2D g2){

    }
}
