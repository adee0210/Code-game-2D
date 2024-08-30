package screen_setting;


import javax.swing.*;

import CollisionChecker.CollisionChecker;
import control_setting.KeyHandler;
import entity.Player;
import object.SuperObject;
import scenery.Tile.TileManager;

import java.awt.*;
/*
    
 */

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTING

    private final int originalTitleSize = 16; // 16x16 title
    private final int scale = 3;
    public final int titleSize = originalTitleSize * scale; // 48 x 48
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 12;
    public final int screenWidth = titleSize * maxScreenCol; // width pixel
    public final int screenHeight =  titleSize * maxScreenRow;

    /*
     * MAPS SETTING
     */

    public final int maxWorldRow = 18;
    public final int maxWorldCol = 19;
    public final int worldWidth = titleSize * maxWorldCol;
    public final int worldHeight = titleSize * maxWorldRow;

    // FPS
    private final int FPS  = 60;



    /*
     * ADD : Thêm các thành phần khác vào đây
     */
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public TileManager tileManager = new TileManager(this);
    public Player player = new Player(this,keyH);
    public AssetSetter assetSetter = new AssetSetter(this);

    /*
     * TẠO RA MẢNG CHỨA CÁC OBJECT
     */
    public SuperObject obj[] = new SuperObject[10];


    /*
     * BUILD : Thiết lập hiển thị màn hình GAME
     */
    public GamePanel() {
       this.setPreferredSize(new Dimension(screenWidth,screenHeight));
       this.setBackground(Color.GRAY);
       this.setDoubleBuffered(true);
       this.addKeyListener(keyH);
       this.setFocusable(true);

    }
    public void setUpGame() {
        assetSetter.setObject();
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        // double drawInterval = 1000000000 / FPS; // 0.01666 second ve lai 1 lan
        // double nextDrawTime = System.nanoTime() + drawInterval;

        // Game loop
        while (gameThread != null) {


            // 1.UPDATE : information such as character positions
            update();

            // 2.DRAW character
            repaint();

//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000;
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            try {
                /*
                 * CÁCH ĐỂ KHỞI TẠO FPS 
                 * 1 tỷ / FPS =  a (giây) , đổi a(giây) thành b(mi li giây)
                 * đưa b vào Thread.sleep(b)
                 */
                Thread.sleep(16); // Tương đương với khoảng 60 lần lặp mỗi giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /*
     * BUILD : Làm hiệu ứng thay đổi thì thêm vào đây để thực thể có thể chuyển động
     */
    public void update() {
        player.update();

    }

    /*
     * ADD : Đưa vào các hàm vẽ vật thể
     * Example : các phương thức là void draw() ở các lớp
     * Lưu ý : Phải tạo đối tượng trước khi đưa phương thức draw ở lớp đó vào phương thức dưới này
     */
    public void paintComponent(Graphics g) {
        // Thừa kế hàm vẽ 
        super.paintComponent(g);

        // Chuyển các image thành 2D
        Graphics2D g2 = (Graphics2D) g;

        /*
         * Lưu ý: Phải vẽ các bối cảnh trước khi vẽ nhân vật, chuyển động nhân vật,...
         */
        // Vẽ tile
        tileManager.draw(g2);

        /*
         * Vẽ object lần lượt có trong mảng
         */
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }

        // Vẽ player
        player.draw(g2);

        
        // Giải phóng tài nguyên sau khi vẽ xong ngay lập tức
        g2.dispose();

    }

}
