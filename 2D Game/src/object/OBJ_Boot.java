package object;

import javax.imageio.ImageIO;

public class OBJ_Boot extends SuperObject{

    public OBJ_Boot() {
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/image_object/boots.png"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        collision = true;
        
    }

}