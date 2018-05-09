import com.sun.javaws.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

/**
 * Created by marsik on 04.05.2018.
 */
public class Launcher extends JFrame {
    static Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    static Image im1;
    static Image im2;
    static Image im3;
    static Image im4;
    static Image im5;
    static Image im6;
    static Image im7;
    static Image im8;
    static Image im9;
    static Image im10;
    static Image im11;
    static Image im12;
    static Image im13;
    JTextField text = new JTextField();
    int scen;
    int mausX;
    int mausY;
    double k, f;
    double t1 = 0;
    AlphaComposite ac;

    public Launcher(String s) {
        super(s);
        setBounds(0, 0, sSize.width, sSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        text.setBackground(new Color(0, 0, 0, 0));
        text.setCaretColor(Color.blue);
        text.setForeground(Color.white);
        text.setSelectionColor(Color.blue);
        text.setSelectedTextColor(Color.green);
        text.setFont(new Font("Dialog", 2, 50));
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        init();
        scen = 0;

        JPanel l = new JPanel() {
            Graphics2D g2;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g2 = (Graphics2D) g;

                k = (double) (getWidth() - 16) / (double) (im1.getWidth(null));
                f = (double) (getHeight() - 48) / (double) (im1.getHeight(null));

                switch (scen) {
                    case 1:
                        scen1(g2);
                        break;
                    case 0:
                        scen0(g2);
                        break;
                    case 2:
                        scen2(g2);
                        break;
                }
                repaint();
            }
        };
        l.add(text);
        l.setLayout(null);
        setContentPane(l);

        l.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mausX = e.getX();
                mausY = e.getY();
            }
        });
        l.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (e.getButton() == MouseEvent.BUTTON1) {
//                    click();
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    click();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        Launcher a = new Launcher("sdf");
    }

    public static void init() {
        try {
            im1 = ImageIO.read(new File("stars.png")); // задний фон
            im2 = ImageIO.read(new File("star_1.png")); // кусок звезды
            im3 = ImageIO.read(new File("star_2.png")); // это тоже кусок звезды
            im4 = ImageIO.read(new File("star_3.png")); // ещё кусок звезды
            im5 = ImageIO.read(new File("menu_button.png")); // кнопка
            im6 = ImageIO.read(new File("menu_all_buttons.png")); // кнопки
            im7 = ImageIO.read(new File("choice1.png")); // кнопки
            im8 = ImageIO.read(new File("choice2.png")); // кнопки
            im9 = ImageIO.read(new File("choice3.png")); // кнопки
            im10 = ImageIO.read(new File("menu_choice.png")); // кнопки
            im11 = ImageIO.read(new File("fighter/sh0.png"));
            im12 = ImageIO.read(new File("cruiser/sh0.png"));
            im13 = ImageIO.read(new File("battleship/sh0.png"));
        } catch (Exception e) {
            System.err.println("У вас траблы с текстурками");
        }
    }

    public void scen1(Graphics2D g2) {
        g2.drawImage(im1, 0, 0, (int) (im1.getWidth(null) * k), (int) (im2.getHeight(null) * f), null);

        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (Math.sin((System.nanoTime() - t1) / 2140000000) + 2) / 3);
        g2.setComposite(ac);
        g2.drawImage(im2, 0, 0, (int) (im2.getWidth(null) * k), (int) (im2.getHeight(null) * f), null);
        g2.drawImage(im3, 0, 0, (int) (im3.getWidth(null) * k), (int) (im3.getHeight(null) * f), null);
        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
        g2.setComposite(ac);
        g2.drawImage(im4, 0, 0, (int) (im4.getWidth(null) * k), (int) (im2.getHeight(null) * f), null);
        g2.drawImage(im6, 0, 0, (int) (im6.getWidth(null) * k), (int) (im6.getHeight(null) * f), null);

        if ((mausY >= im1.getHeight(null) * f / 5) &&
                (mausY <= im1.getHeight(null) * f * 2 / 5) &&
                (mausX <= im1.getWidth(null) * k / 2) &&
                (mausX >= im1.getWidth(null) * k / 10)) {
            g2.drawImage(im7, 0, 0, (int) (im7.getWidth(null) * k), (int) (im7.getHeight(null) * f), null);
        }
        if ((mausY >= im1.getHeight(null) * f * 2 / 5) &&
                (mausY <= im1.getHeight(null) * f * 3 / 5) &&
                (mausX <= im1.getWidth(null) * k / 2) &&
                (mausX >= im1.getWidth(null) * k / 10)) {
            g2.drawImage(im8, 0, 0, (int) (im8.getWidth(null) * k), (int) (im8.getHeight(null) * f), null);
        }
        if ((mausY >= im1.getHeight(null) * f * 3 / 5) &&
                (mausY <= im1.getHeight(null) * f * 4 / 5) &&
                (mausX <= im1.getWidth(null) * k / 2) &&
                (mausX >= im1.getWidth(null) * k / 10)) {
            g2.drawImage(im9, 0, 0, (int) (im9.getWidth(null) * k), (int) (im9.getHeight(null) * f), null);
        }
    }

    public void scen0(Graphics2D g2) {
        g2.drawImage(im1, 0, 0, (int) (im1.getWidth(null) * k), (int) (im1.getHeight(null) * f), null);
        g2.drawImage(im5,
                (int) ((im1.getWidth(null) / 2 - im5.getWidth(null) / 2) * k),
                (int) (im1.getHeight(null) / 2 * f),
                (int) (im5.getWidth(null) * k), (int) (im5.getHeight(null) * f), null
        );
        text.setBounds((int) ((im1.getWidth(null) / 2 - im5.getWidth(null) / 2) * k),
                (int) ((im1.getHeight(null) / 2 - im5.getHeight(null)) * f - 10),
                (int) (im5.getWidth(null) * k),
                (int) (im5.getHeight(null) * f)
        );

        if ((mausY >= im1.getHeight(null) * f / 2) &&
                (mausY <= (im1.getHeight(null) / 2 + im5.getHeight(null)) * f) &&
                (mausX >= (im1.getWidth(null) / 2 - im5.getWidth(null) / 2) * k) &&
                (mausX <= (im1.getWidth(null) / 2 + im5.getWidth(null) / 2) * k)) {
            g2.drawImage(im10,
                    (int) ((im1.getWidth(null) / 2 - im5.getWidth(null) / 2) * k),
                    (int) (im1.getHeight(null) / 2 * f),
                    (int) (im5.getWidth(null) * k), (int) (im5.getHeight(null) * f), null
            );
        }
    }

    public void scen2(Graphics2D g2) {
        g2.drawImage(im1, 0, 0, (int) (im1.getWidth(null) * k), (int) (im2.getHeight(null) * f), null);

        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (Math.sin((System.nanoTime() - t1) / 2140000000) + 2) / 3);
        g2.setComposite(ac);

        g2.drawImage(im2, 0, 0, (int) (im2.getWidth(null) * k), (int) (im2.getHeight(null) * f), null);
        g2.drawImage(im3, 0, 0, (int) (im3.getWidth(null) * k), (int) (im3.getHeight(null) * f), null);

        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
        g2.setComposite(ac);

        g2.drawImage(im4, 0, 0, (int) (im4.getWidth(null) * k), (int) (im2.getHeight(null) * f), null);
        g2.drawImage(im6, 0, 0, (int) (im6.getWidth(null) * k), (int) (im6.getHeight(null) * f), null);

        if ((mausY >= im1.getHeight(null) * f / 5) &&
                (mausY <= im1.getHeight(null) * f * 2 / 5) &&
                (mausX <= im1.getWidth(null) * k / 2) &&
                (mausX >= im1.getWidth(null) * k / 10)) {
            g2.drawImage(im7, 0, 0, (int) (im7.getWidth(null) * k), (int) (im7.getHeight(null) * f), null);
            g2.drawImage(im11,
                    (int) (im1.getWidth(null) * 3 / 4 * k - im1.getHeight(null) * 3 * f / 5 / im11.getHeight(null) * im11.getWidth(null) / 2),
                    (int) (im1.getHeight(null) / 5 * f),
                    (int) (im1.getHeight(null) * 3 * f / 5 / im11.getHeight(null) * im11.getWidth(null)),
                    (int) (im1.getHeight(null) * 3 * f / 5), null
            );
        }
        if ((mausY >= im1.getHeight(null) * f * 2 / 5) &&
                (mausY <= im1.getHeight(null) * f * 3 / 5) &&
                (mausX <= im1.getWidth(null) * k / 2) &&
                (mausX >= im1.getWidth(null) * k / 10)) {
            g2.drawImage(im8, 0, 0, (int) (im8.getWidth(null) * k), (int) (im8.getHeight(null) * f), null);
            g2.drawImage(im12,
                    (int) (im1.getWidth(null) * 3 / 4 * k - im1.getHeight(null) * 3 * f / 5 / im12.getHeight(null) * im12.getWidth(null) / 2),
                    (int) (im1.getHeight(null) / 5 * f),
                    (int) (im1.getHeight(null) * 3 * f / 5 / im12.getHeight(null) * im12.getWidth(null)),
                    (int) (im1.getHeight(null) * 3 * f / 5), null
            );
        }
        if ((mausY >= im1.getHeight(null) * f * 3 / 5) &&
                (mausY <= im1.getHeight(null) * f * 4 / 5) &&
                (mausX <= im1.getWidth(null) * k / 2) &&
                (mausX >= im1.getWidth(null) * k / 10)) {
            g2.drawImage(im9, 0, 0, (int) (im9.getWidth(null) * k), (int) (im9.getHeight(null) * f), null);
            g2.drawImage(im13,
                    (int) (im1.getWidth(null) * 3 / 4 * k - im1.getHeight(null) * 3 * f / 5 / im13.getHeight(null) * im13.getWidth(null) / 2),
                    (int) (im1.getHeight(null) / 5 * f),
                    (int) (im1.getHeight(null) * 3 * f / 5 / im13.getHeight(null) * im13.getWidth(null)),
                    (int) (im1.getHeight(null) * 3 * f / 5), null
            );
        }
    }

    public void click() {
        switch (scen) {
            case 0:
                if ((mausY >= im1.getHeight(null) * f / 2) &&
                        (mausY <= (im1.getHeight(null) / 2 + im5.getHeight(null)) * f) &&
                        (mausX >= (im1.getWidth(null) / 2 - im5.getWidth(null) / 2) * k) &&
                        (mausX <= (im1.getWidth(null) / 2 + im5.getWidth(null) / 2) * k)) {
                    if (text.getText().equals("")) {
                        text.setBorder(BorderFactory.createLineBorder(Color.RED));
                    } else {
                        text.setVisible(false);
                        scen = 1;
                        t1 = System.nanoTime();
                    }
                }
                break;
            case 1:
                if ((mausY >= im1.getHeight(null) * f / 5) &&
                        (mausY <= im1.getHeight(null) * f * 2 / 5) &&
                        (mausX <= im1.getWidth(null) * k / 2) &&
                        (mausX >= im1.getWidth(null) * k / 10)) {
                    scen = 2;
                }
                if ((mausY >= im1.getHeight(null) * f * 2 / 5) &&
                        (mausY <= im1.getHeight(null) * f * 3 / 5) &&
                        (mausX <= im1.getWidth(null) * k / 2) &&
                        (mausX >= im1.getWidth(null) * k / 10)){
                    JOptionPane.showMessageDialog(null,"Сервер не найден");
                }
                if ((mausY >= im1.getHeight(null) * f * 3 / 5) &&
                        (mausY <= im1.getHeight(null) * f * 4 / 5) &&
                        (mausX <= im1.getWidth(null) * k / 2) &&
                        (mausX >= im1.getWidth(null) * k / 10)) {

                }

                break;
            case 2:
                if ((mausY >= im1.getHeight(null) * f / 5) &&
                        (mausY <= im1.getHeight(null) * f * 2 / 5) &&
                        (mausX <= im1.getWidth(null) * k / 2) &&
                        (mausX >= im1.getWidth(null) * k / 10)) {
                    Main.init(1);
                }
                if ((mausY >= im1.getHeight(null) * f * 2 / 5) &&
                        (mausY <= im1.getHeight(null) * f * 3 / 5) &&
                        (mausX <= im1.getWidth(null) * k / 2) &&
                        (mausX >= im1.getWidth(null) * k / 10)){
                    Main.init(2);
                }
                if ((mausY >= im1.getHeight(null) * f * 3 / 5) &&
                        (mausY <= im1.getHeight(null) * f * 4 / 5) &&
                        (mausX <= im1.getWidth(null) * k / 2) &&
                        (mausX >= im1.getWidth(null) * k / 10)) {
                    Main.init(3);
                }
                setVisible(false);
                break;

        }
    }
}
