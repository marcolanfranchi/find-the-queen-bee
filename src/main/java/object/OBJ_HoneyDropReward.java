package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_HoneyDropReward extends SuperObject {
	public OBJ_HoneyDropReward() {
		name = "Honey";

		try {
			image = ImageIO.read(getClass().getResource("../ui/images/HoneyDrop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = 48;
		height = 48;
 

	}
}
