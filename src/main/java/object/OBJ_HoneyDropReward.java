package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_HoneyDropReward extends SuperObject {

	public int rewardValue;
	public OBJ_HoneyDropReward() {
		name = "Honey";

		try {
			image = ImageIO.read(getClass().getResource("../ui/images/HoneyDrop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = 48;
		height = 48;

		rewardValue = 10;
	}




}
