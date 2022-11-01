package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_ARROW extends SuperObject {
	public OBJ_ARROW() {
		name = "ArrowKeys";

		try {
			image = ImageIO.read(getClass().getResource("../ui/images/arrowKeys.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
