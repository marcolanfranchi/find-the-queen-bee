package object;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Entity.Bee;

public class OBJ_TextBubble extends SuperObject {

    BufferedImage missingRewardsImg;

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
