import javax.swing.*;
import java.awt.*;


/**
 * Created by Vova on 22.01.2018.
 */
public class MyFrame extends JFrame {

    public MyFrame(String s){
        super(s);
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        setBounds(0,0, sSize.width, sSize.height-40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new MyKeyListener());
        addMouseListener(new MyMouseListener());
        addMouseWheelListener(new MyMouseWheelListener());
        addMouseMotionListener(new MyMouseMotionListener());
        add(new Grafonium());


        setVisible(true);
    }
}
