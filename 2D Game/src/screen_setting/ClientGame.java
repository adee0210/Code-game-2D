package screen_setting;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
    /*
     * CLIENT : Giống như 1 APP trên desktop khi ấn vào sẽ chạy game
     * 
     */
public class ClientGame {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setTitle("2D Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        BufferedImage image = null;
        try {
            // URL url = new URL("");
            File file = new File("C:/Users/admin/Downloads/hinh-nen-3d-gaming-4k_507102.jpg");
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        window.setIconImage(image);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();  // Tính toán kích thước của cửa sổ dựa trên các thành phần

        window.setLocationRelativeTo(null);  // Đặt vị trí của cửa sổ ở giữa màn hình sau khi kích thước đã được xác định

        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}
