package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class QueenBee extends SuperObject {

    public QueenBee() {
        name = "Queen Bee";
        try {
            image = ImageIO.read(getClass().getResource("../ui/images/QueenBee1.png"));
        } catch (IOException e) {
            e.printStackTrace();
		}
        width = 48 * 2;
        height = 48 * 2;

        }  
}
