package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class represents a Honey Drop image which is used 
 * in the UI next to the player's number of rewards collected.
 * 
 * @author Satvik Garg
 */
public class OBJ_HoneyDrop extends SuperObject {

	/**
	 * Creates an instance of a Honey Drop with the appropriate image and sets 
	 * its width and height.
	 */
	public OBJ_HoneyDrop() {
		name = "Honey";

		try {
			image = ImageIO.read(getClass().getResource("/ui/images/HoneyDrop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = 48;
		height = 48;
	}
}
