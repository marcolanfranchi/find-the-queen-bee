package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class is used to create a WASD Key Image object.
 */
public class OBJ_WASD extends SuperObject {
	public OBJ_WASD() {
		name = "WASDKeys";

		//Load the image into the image variable defined in the SuperObject class.
		try {
			image = ImageIO.read(getClass().getResource("../ui/images/WASDKeys.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
