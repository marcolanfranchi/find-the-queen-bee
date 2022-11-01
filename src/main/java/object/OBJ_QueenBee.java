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

    public void update() {
        if (count == 1) {
            try {
                image = ImageIO.read(getClass().getResource("../ui/images/QueenBee-2.png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            count++;
        } else {
            if (count == 2) {
                try {
                    image = ImageIO.read(getClass().getResource("../ui/images/QueenBee-2.png"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count = 0;
            }
        }
    }
    
    public void updateBee() {
        this.update();
    }
}
