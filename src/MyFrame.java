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

        JPanel graf = new JPanel(){
            Graphics2D g2;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g2 = (Graphics2D) g;

                Data.gp.draw(g2);

                repaint();
            }
        };

        setContentPane(graf);

        addKeyListener(new MyKeyListener());
        addMouseListener(new MyMouseListener());
        addMouseWheelListener(new MyMouseWheelListener());
        addMouseMotionListener(new MyMouseMotionListener());

        setVisible(true);
    }
}
