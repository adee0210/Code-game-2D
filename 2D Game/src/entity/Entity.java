package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
    Lớp Entity là đại diện cho các thực thể(mang hình ảnh cụ thể trong game) như NPC, Nhân vật, Quái,...
    Các QUY ĐỊNH để gọi là 1 ENTITY và thừa kế được từ lớp này:
    - Có khả năng có thể thay đổi được toạ độ của chính nó
    - Phải có hình ảnh đại diện cho lớp đó
 */
public class Entity {
    public int worldX;
    public int worldY;
    public int x;
    public int y;
    public int speed;
    public BufferedImage up1,up2,
            right1,right2,right3,right4,right5,right6,right7,right8,
            left1,left2,left3,left4,left8,left5,left6,left7,
            down1,down2;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    // THIẾT LẬP VẬT THỂ RẮN
    public Rectangle solidArea;

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

}
