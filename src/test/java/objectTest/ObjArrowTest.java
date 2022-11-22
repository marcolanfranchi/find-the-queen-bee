package objectTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import object.OBJ_ARROW;

public class ObjArrowTest {
	@Test
	public void testInstance() {
		OBJ_ARROW arrow = new OBJ_ARROW();
		assertNotNull(arrow);
	}

}
