import javax.swing.*;


/**
 * Created by Vova on 22.01.2018.
 */
public class MyFrame extends JFrame {

    public MyFrame(String s){
        super(s);
        setBounds(0,0, Data.sSize.width, Data.sSize.height-40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new MyKeyListener());
        addMouseListener(new MyMouseListener());
        addMouseWheelListener(new MyMouseWheelListener());
        addMouseMotionListener(new MyMouseMotionListener());
        add(new Grafonium());


        setVisible(true);
    }
}
