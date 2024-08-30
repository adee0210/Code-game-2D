package CollisionChecker;

import entity.Entity;
import screen_setting.GamePanel;

public class CollisionChecker {
    /*
     * CHECK : KIỂM TRA CÁC CHƯỚNG NGẠI VẬT, BAO GỒM CẢ QUÁI,NPC,...
     * KIỂM TRA ĐỂ THỰC HIỆN CÁC CHỨC NĂNG : KHÔNG ĐƯỢC ĐI VÀO Ô NÀY,...
     */

    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /*
     * CHECK : KIỂM TRA MỘT THỰC THỂ NÀO ĐÓ 
     */
    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;

        int entityTopWorldY = entity.worldY + entity.solidArea.y;

        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.titleSize;
        int entityRightCol = entityRightWorldX / gp.titleSize;
        int entityTopRow = entityTopWorldY / gp.titleSize;
        int entityBottomRow = entityBottomWorldY / gp.titleSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":

            entityTopRow = (entityTopWorldY - entity.speed) / gp.titleSize;
            tileNum1 = gp.tileManager.matrix_map[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileManager.matrix_map[entityRightCol][entityTopRow];

            if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        
            case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.titleSize;
            tileNum1 = gp.tileManager.matrix_map[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileManager.matrix_map[entityRightCol][entityBottomRow];

            if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
        }
            break;

            case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.titleSize;
            tileNum1 = gp.tileManager.matrix_map[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileManager.matrix_map[entityLeftCol][entityBottomRow];

            if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;

            case "right":
            entityRightCol = (entityRightWorldX + entity.speed) / gp.titleSize;
            tileNum1 = gp.tileManager.matrix_map[entityRightCol][entityTopRow];
            tileNum2 = gp.tileManager.matrix_map[entityRightCol][entityBottomRow];

            if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;

       }
       
    }
    public int checkObject(Entity entity,boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                /*
                 * LẤY RA VÙNG KIỂM TRA CỦA THỰC THỂ
                 */
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                /*
                 * Lấy ra vùng kiểm tra của object
                 */
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                /*
                 * KIỂM TRA CÁC EVENT LIÊN QUAN ĐẾN THỰC THỂ
                 * HÀM ,intersects dùng kiểm tra giữa vật rắn thực thể và vận rắn của ô obj xem có giao nhau hay không
                 */
                switch (entity.direction) {
                    case "up":
                     entity.solidArea.y -= entity.speed;
                     if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if ( player == true) {
                            index = i;
                            
                        }
                        
                     }
                        break;

                     case "left":
                     entity.solidArea.x -= entity.speed;
                     if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if ( player == true) {
                            index = i;
                        }
                     }

                        break;
                    
                    case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if ( player == true) {
                            index = i;
                            
                        }
                    }
                        break;
                        
                    // case "down":
                    // entity.solidArea.y += entity.speed;
                    // if (entity.solidArea.intersects(gp.obj[i].solidArea)) {

                        
                    // }
                    //     break;
                
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
            gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            
        }
    }
        return index;
        
        }
}
