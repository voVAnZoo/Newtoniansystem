import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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
        //addMouseListener(new MyMouseListener());
        //addMouseWheelListener(new MyMouseWheelListener());
        //addMouseMotionListener(new MyMouseMotionListener());

        Grafonium a = new Grafonium();
        a.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
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
        add(a);
        setVisible(true);
    }
}
