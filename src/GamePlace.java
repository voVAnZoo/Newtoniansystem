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

    Image img;
    public GamePlace(){
        try {
            img = ImageIO.read(new File("res/maxresdefault.png"));
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
}
