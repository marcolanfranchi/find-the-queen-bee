package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_QueenBee extends SuperObject {

    public int count = 1;

    public OBJ_QueenBee() {
        name = "Queen Bee";
        try {
            image = ImageIO.read(getClass().getResource("../ui/images/QueenBee-1.png"));
        } catch (IOException e) {
            e.printStackTrace();
		}
        width = 48;
        height = 48;
        }
}
