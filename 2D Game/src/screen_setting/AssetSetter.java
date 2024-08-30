package screen_setting;

import object.OBJ_Boot;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        /*
         * Đặt các object ở trong MAP
         * Số đầu tiên nhân với gp.titleSize chính là vị trí của nó theo bản đồ world (trong map dạng matrix)
         */
        gp.obj[0] =  new OBJ_Key();
        gp.obj[0].worldX = 1 * gp.titleSize;
        gp.obj[0].worldY = 1 * gp.titleSize;

        gp.obj[1] =  new OBJ_Key();
        gp.obj[1].worldX = 5 * gp.titleSize;
        gp.obj[1].worldY = 11 * gp.titleSize;

        gp.obj[2] =  new OBJ_Door();
        gp.obj[2].worldX = 12 * gp.titleSize;
        gp.obj[2].worldY = 2 * gp.titleSize;

        gp.obj[3] = new OBJ_Boot();
        gp.obj[3].worldX = 13 * gp.titleSize;
        gp.obj[3].worldY = 3 * gp.titleSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 15 * gp.titleSize;
        gp.obj[4].worldY = 3 * gp.titleSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 1 * gp.titleSize;
        gp.obj[5].worldY = 13 * gp.titleSize;
        



    }

}
