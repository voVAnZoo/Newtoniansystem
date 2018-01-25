import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Vova on 22.01.2018.
 */
public class MyMouseMotionListener implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {

        Data.camX += Data.mcamX - e.getX();
        Data.camY += Data.mcamY - e.getY();
        Data.mcamX = e.getX();
        Data.mcamY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
