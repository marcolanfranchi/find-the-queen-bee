package object;

import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * This class represents a text Bubble Object with an image which is used 
 * to act as a message coming from the Queen Bee.
 * 
 * @author Marco Lanfranchi
 */
public class OBJ_TextBubble extends SuperObject {

    /**
     * Creates an instance of a Text Bubble object with an image displaying a 
     * text bubble "down here" and set its height and width.
     */
    public OBJ_TextBubble() {
		name = "Down Here Text Bubble";
        try {
            image = ImageIO.read(getClass().getResource("../ui/images/DownHereText.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
		}
        width = 96;
        height = 64;
        }
        
        /**
         * Sets this Text Bubble Object's image to an image displaying a text bubble
         * "Bring all the honey" used to remind the player to collect all honey rewards.
         */
        public void setAltImage() {
            try {
                image = ImageIO.read(getClass().getResource("../ui/images/ForgotHoneyText.png"));
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
