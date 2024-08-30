package object;


import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/image_object/door.png"));
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        collision = true;
    }


}
