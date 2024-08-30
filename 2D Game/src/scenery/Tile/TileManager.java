package scenery.Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import screen_setting.GamePanel;

/**
 * TileManager
 */
public class TileManager {
    
    GamePanel gp;
    /*
     * Biến Tile là mảng chứa các thể loại gạch khác nhau
     * @param 
     */
    public Tile[] tile;
    public int[][] matrix_map; 
    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];

        matrix_map = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();

        readMap("/scenery/image_scenery/matrix_maps_edit.txt");
    }
    public void getTileImage() {
        try {
            // 3 HOA
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/scenery/image_scenery/grass/sprite_0.png"));

            // MỘT HOA 
            tile[1] =  new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/scenery/image_scenery/grass/sprite_1.png"));

            // CỎ KHÔNG
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/scenery/image_scenery/grass/sprite_2.png"));

            // WATER
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/scenery/image_scenery/grass/sprite_3.png"));

            // BUILD: KHÔNG ĐI QUA ĐƯỢC CÁC TILE NÀY
            // KHÔNG DC ĐI QUA BLOCK WATER
            tile[2].collision = true;



            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readMap(String filePath) {
        try {
            InputStream inputMatrix_map = getClass().getResourceAsStream(filePath);
            BufferedReader readInput = new BufferedReader(new InputStreamReader(inputMatrix_map));

            int col = 0;
            int row = 0;
            while (row < gp.maxWorldRow) {
                String line = readInput.readLine();
                if (line == null) {
                    break;  // Exit if end of file is reached
                }

                String[] numbers = line.split(" ");
                if (numbers.length != gp.maxWorldCol) {
                    throw new IllegalArgumentException("Invalid number of columns in the map file at row " + row);
                }

                while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    matrix_map[col][row] = num;
                    col++;
                }
                
                col = 0;
                row++;
            }
            readInput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * TẠO CỎ
     */
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = matrix_map[worldCol][worldRow];
            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
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
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize,null);
            }

            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
                
            }  
        }  
    } 
}