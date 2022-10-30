package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Honey extends SuperObject {
	public OBJ_Honey() {
		name = "Honey";

		try {
			image = ImageIO.read(getClass().getResource("../ui/images/HoneyDrop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
