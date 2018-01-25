import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Vova on 22.01.2018.
 */
public class MyMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Data.mcamX = e.getX();
        Data.mcamY = e.getY();
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
}
