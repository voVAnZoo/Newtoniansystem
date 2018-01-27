import javax.swing.*;

/**
 * Created by Vova on 22.01.2018.
 */
public class Main {
    public static void main(String[] args) {
        init();
    }

    public static void init(){

        Data.gp = new GamePlace();
        Psub i = new Psub(1,0,0,0,0,0.01,0.01,0, 1);
        Data.gp.add(i);
        new MyFrame("Newtonian's system simulator");
        Timer timer = new Timer(Data.friq, new MyActionListener());
        timer.start();
    }
}
