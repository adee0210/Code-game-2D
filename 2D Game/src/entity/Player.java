package entity;

import control_setting.KeyHandler;
import screen_setting.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


// CRATE: Tạo 1 class Nhân vật thuộc lớp Entity(Thực thể)
public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    /*
     * GIỐNG NHƯ LIA MAPS TRONG LQ, NÓ SẼ DI CHUYỂN MAPS THEO NGƯỜI CHƠI
     */
    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();

        screenX = gp.screenWidth / 2 - (gp.titleSize / 2);
        screenY = gp.screenHeight / 2 - (gp.titleSize / 2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        /* 
         * Tạo một ô vuông cho vào người chơi, ô vuông đó có tác dụng nhận biết các chướng ngại vật
         * Ô VUÔNG SO VỚI PLAYER, KHÔNG SO VỚI MAP
         */
        solidArea = new Rectangle(8,16,32,32);
    }

    public void setDefaultValues() {
        /*
         * VỊ TRÍ CỦA PLAYER SO VỚI MAPS
         */
        worldX = gp.titleSize * 5;
        worldY = gp.titleSize * 5;
        speed = 3;
        direction = "up";
    }

    // DRAW : Các hình ảnh tương ứng với các chuyển động của nhân vật
    public void getPlayerImage() {
        try {
            // image tuong ung voi cac thuoc tinh nut
            up1 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move down/hero_16x16_idle_north1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move down/hero_16x16_idle_north2.png"));

            /*
             * BUILD : Gắn hoạt ảnh -> Di chuyển sang phải
             */

            right1 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move right/l0_sprite_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move right/l0_sprite_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move right/l0_sprite_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move right/l0_sprite_4.png"));
            
            /*
             * BUILD : Gắn hoạt ảnh -> Di chuyển sang trái
             */

            left1 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move left/sprite_0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move left/sprite_1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move left/sprite_2.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream(
                    "/image_entity/character's_actions/character's_movements/move left/sprite_3.png"));
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // UPDATE: mỗi lần khi có một nút bấm xảy ra
    public void update() {
        // Khi nào ấn 1 trong 4 nút mới làm nó chuyển động
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {
            if (keyH.upPressed) {
                direction = "up";

            }
             else if (keyH.downPressed) {
                direction = "down";
                
            } 
            else if (keyH.leftPressed) {
                direction = "left";

            } 
            else if (keyH.rightPressed) {
                direction = "right";

            }

            // CHECK : CHỈNH SỬA CÁC HÀNH VI CỦA PLAYER KHI GẶP CÁC CHƯỚNG NGẠI VẬT

            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            // NẾU COLLISION LÀ FALSE, PLAYER ĐỨNG YÊN VÀ NGƯỢC LẠI
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                    worldY -= speed;
                    break;
                    
                    case "down":
                    worldY += speed;
                    break;
                
                    case "left":
                    worldX -= speed;
                    break;

                    case "right":
                    worldX += speed;
                    break;
                }

                
            }
            spriteCounter++;

            // thay doi trong moi 12 khung hinh
            if (spriteCounter > 12) {
                spriteNum++;
                if (spriteNum > 4) { // Assuming you have 8 sprites for the right direction
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
        
    }
    /*
     * HÀM NHẶT ĐƯỢC 1 OBJECT GÌ ĐÓ THÔNG BÁO CHO CÁC HÀM ĐIỀU KHIỂN
     */
    public void pickUpObject(int i) {
        if (i != 999) {
            // Tên của object khi chạm vào
            String objectName = gp.obj[i].name;
            switch (objectName) {
                // Đưa các tên ở lớp object vào đây
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("key: "+hasKey); 
                    break;
                
                case "Boots":
                speed += 1;
                gp.obj[i] = null;
                    break;

                case "Door":
                while (hasKey > 0) {
                    gp.obj[i] = null;
                    hasKey--;
                }
                    break;
                
            } 
        }
    }


    // DRAW : Các chuyển động sẽ được vẽ lại khi thỏa mãn yêu cầu nào đó về hình ảnh
    /**
     * @param g2
     *
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                switch (spriteNum) {
                    case 1 -> image = up1;
                    case 2 -> image = up2;
                    case 3 -> image = up2;
                    case 4 -> image = up1;
                    
                }
                break;
            case "left":
                switch (spriteNum) {
                    case 1 -> image = left1;
                    case 2 -> image = left2;
                    case 3 -> image = left3;
                    case 4 -> image = left4;
                    
                }
                break;

            case "right":
                switch (spriteNum) {
                    case 1 -> image = right1;
                    case 2 -> image = right2;
                    case 3 -> image = right3;
                    case 4 -> image = right4;
                    
                }
                break;
            case "down":
                switch (spriteNum) {
                    case 1 -> image = right1;
                    case 2 -> image = right2;
                    case 3 -> image = right3;
                    case 4 -> image = right4;
                    
                }

                break;

        }

        // DRAW : Đặt hình ảnh ban đầu cho nhân vật(image), tọa độ ban đầu, Kích thước
        // của nhân vật
        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
    }
}
