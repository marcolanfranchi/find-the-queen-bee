package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_WASD extends SuperObject {
	public OBJ_WASD() {
		name = "WASDKeys";

		try {
			image = ImageIO.read(getClass().getResource("../ui/images/WASDKeys.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
