import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marsik on 04.05.2018.
 */
public class Launcher extends JFrame{
    static Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();

    public static void main(String[] args) {
        Launcher a = new Launcher("sdf");
        //a.startmenu();
    }

    public Launcher(String s){
        super(s);
        setBounds(0,0,sSize.width,sSize.height);
        //setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GrafoniumLaunch(this));
        setVisible(true);
    }

    public void startmenu(){
        JTextField text = new JFormattedTextField();
        text.setBounds(100,100,300,40);

        JButton start = new JButton("start");
        start.setBounds(100,180,300,40);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!text.getText().equals("")){
                    text.setVisible(false);
                    start.setVisible(false);
                    chous();
                }
            }
        });
        this.add(text);
        this.add(start);
        this.repaint();
    }

    public void chous(){
        JButton f = new JButton("Fighter");
        JButton c = new JButton("Cruiser");
        JButton b = new JButton("Battle ship");

        f.setBounds(100,100,300,40);
        c.setBounds(100,180,300,40);
        b.setBounds(100,260,300,40);

        f.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                c.setVisible(false);
                b.setVisible(false);
                sorc(1);
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                c.setVisible(false);
                b.setVisible(false);
                sorc(2);
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                c.setVisible(false);
                b.setVisible(false);
                sorc(3);
            }
        });

        add(f);
        add(c);
        add(b);
        repaint();
    }

    public void sorc(int i){
        JButton s = new JButton("Server");
        JButton c = new JButton("Client");

        s.setBounds(100,100,300,40);
        c.setBounds(100,180,300,40);

        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.init(i);
                setVisible(false);
            }
        });

        add(s);
        add(c);

        repaint();
    }
}
