import javax.swing.*;
import java.awt.*;

/**
 * Created by Vova on 22.01.2018.
 */
public class Grafonium extends JComponent {

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2=(Graphics2D)g;

        Data.gp.draw(g2);

        super.repaint();
    }
}
