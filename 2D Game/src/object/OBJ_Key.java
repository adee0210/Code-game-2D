package object;

import javax.imageio.ImageIO;


public class OBJ_Key extends SuperObject {

    public OBJ_Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/image_object/key.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
