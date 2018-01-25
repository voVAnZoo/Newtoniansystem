import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vova on 22.01.2018.
 */
public class MyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Data.gp.action();
    }
}
