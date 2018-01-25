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
            Server.start();
        }
        if(e.getKeyCode() == KeyEvent.VK_C && Data.ctrl){
            Client.start(JOptionPane.showInputDialog("введите IP"));
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
    }
}
