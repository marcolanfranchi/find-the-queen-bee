package object;

import java.io.IOException;
import javax.imageio.ImageIO;



public class OBJ_TextBubble extends SuperObject {


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
        
        public void setAltImage() {
            try {
                image = ImageIO.read(getClass().getResource("../ui/images/ForgotHoneyText.png"));
                
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
