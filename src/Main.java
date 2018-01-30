import javax.swing.*;

/**
 * Created by Vova on 22.01.2018.
 */
public class Main {
    public static void main(String[] args) {
        try {
            init(Integer.parseInt(args[0]));
        }catch (Exception e){
            init(1);
        }
    }

    public static void init(int t){
        Data.gp = new GamePlace();
        Psub i = new Psub(1,0,0, t);
        i.isActiv = true;
        Data.gp.add(i);
        Data.jf = new MyFrame("Newtonian's system simulator");
        Data.timer = new Timer(Data.friq, new MyActionListener());
        Data.timer.start();
    }
}
