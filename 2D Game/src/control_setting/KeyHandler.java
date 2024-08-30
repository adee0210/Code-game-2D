package control_setting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


    /*
    BUILD : Xây dựng các nút để thực hiện các hành động của 1 NV
     */
public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed,leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }


    // Gắn key cho các thuộc tính
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;

        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;

        }
    }

    // khi nhả phím sẽ đặt nút = false => có thể dừng nút đó lại
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;

        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;

        }
    }
}