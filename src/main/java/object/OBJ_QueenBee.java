package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class represents a text Bubble Object with an image which is used 
 * to display the Queen Bee.
 * 
 * @author Marco Lanfranchi
 */
public class OBJ_QueenBee extends SuperObject {

    /**
     * Creates an instance of a Queen Bee Object and sets its image to the
     * appropriate image and sets its width and height.
     */
    public OBJ_QueenBee() {
        name = "Queen Bee";
        try {
            image = ImageIO.read(getClass().getResource("/ui/images/QueenBee-1.png"));
        } catch (IOException e) {
            e.printStackTrace();
		}
        width = 48;
        height = 48;
        }
}
