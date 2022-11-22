package objectTest;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import object.OBJ_WASD;

public class OBJ_WASDTest {
	@Test
	public void testInstance() {
		OBJ_WASD wasdKeys = new OBJ_WASD();
		assertNotNull(wasdKeys);
	}
}
