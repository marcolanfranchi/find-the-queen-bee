package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class is used to create a Arrow Key Image object.
 */
public class OBJ_ARROW extends SuperObject {
	public OBJ_ARROW() {
		name = "ArrowKeys";

		// Load the image into the image variable defined in the SuperObject class.
		try {
			image = ImageIO.read(getClass().getResource("/ui/images/arrowKeys.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
