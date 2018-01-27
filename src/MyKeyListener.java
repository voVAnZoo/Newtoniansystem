import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Vova on 22.01.2018.
 */
public class MyKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            Data.w = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            Data.a = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            Data.s = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            Data.d = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            Data.shift = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_CONTROL){
            Data.ctrl = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_N && Data.ctrl){
            if(Data.servWork){
                Data.servWork = false;
            }else {
                Server.start();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_C && Data.ctrl) {
            if (Data.clientWork) {
                Data.clientWork = false;
            } else {
                Client.start(JOptionPane.showInputDialog("введите IP"));
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_Q){
            Data.q = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_E){
            Data.e = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            Data.w = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            Data.a = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            Data.s = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            Data.d = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            Data.shift = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_CONTROL){
            Data.ctrl = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_Q){
            Data.q = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_E){
            Data.e = false;
        }
    }
}
