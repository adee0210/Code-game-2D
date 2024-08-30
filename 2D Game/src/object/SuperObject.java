package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import screen_setting.GamePanel;

/*
 * CLASS LA KHUNG DE TAO RA CAC OBJECT KHAC
 */

public class SuperObject {
    public BufferedImage image;
    public boolean collision = false;
    public String name;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int worldX, worldY;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            /*
             * Tạo khung di chuyển map theo người chơi
             */
            if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) 
                {
                g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize,null);
            }
    }

}
