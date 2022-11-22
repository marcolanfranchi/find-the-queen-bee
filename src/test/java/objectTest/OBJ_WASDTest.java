package objectTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import object.OBJ_WASD;

public class OBJ_WASDTest {
	private OBJ_WASD wasdKeys;

	@BeforeEach
	void setup() {
		wasdKeys = new OBJ_WASD();
	}

	@Test
	public void testInstance() {
		String nameResult = wasdKeys.name;
		assertEquals("WASDKeys", nameResult);
		BufferedImage imgResult = wasdKeys.image;
		assertNotNull(imgResult);
	}
}
