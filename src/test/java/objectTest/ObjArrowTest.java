package objectTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import object.OBJ_ARROW;

public class ObjArrowTest {
	private OBJ_ARROW arrowKeys;

	@BeforeEach
	void setup() {
		arrowKeys = new OBJ_ARROW();
	}

	@Test
	public void testInstance() {
		String nameResult = arrowKeys.name;
		System.out.println(nameResult);
		assertEquals("ArrowKeys", nameResult);
		BufferedImage imgResult = arrowKeys.image;
		assertNotNull(imgResult);
	}

}
