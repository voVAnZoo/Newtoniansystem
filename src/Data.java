import javax.swing.*;
import java.awt.*;

/**
 * Created by Vova on 22.01.2018.
 */
public class Data {
    //public static Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();

    public static int friq = 1;

    public static boolean w = false;
    public static boolean a = false;
    public static boolean s = false;
    public static boolean d = false;
    public static boolean q = false;
    public static boolean e = false;
    public static boolean shift = false;
    public static boolean ctrl = false;

    public static GamePlace gp = null;
    public static JFrame jf = null;
    public static Timer timer = null;


    public static double camFactor = 1.2;
    public static double mcamX = 0;
    public static double mcamY = 0;
    public static double camX = 0;
    public static double camY = 0;
    public static double camSize = 1;

    public static boolean servWork = false;
    public static boolean clientWork = false;

    public static class sSize{
        public static int width = 19000;
        public static int height = 10000;
    }

}
