import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by Vova on 22.01.2018.
 */
public class MyMouseWheelListener implements MouseWheelListener {
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() == 1){
            Data.camSize *= Data.camFactor;
        }else{
            Data.camSize /= Data.camFactor;
        }
    }
}
