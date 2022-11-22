package objectTest;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GamePanel;
import object.ObjectManager;

public class ObjectManagerTest {
	ObjectManager objectManager;
	GamePanel gp;

	@BeforeEach
	public void setUp() throws Exception {
		// Define new game panel
		gp = new GamePanel();
		objectManager = new ObjectManager(gp);
	}

	@Test
	public void testObjectManagerInstance() {
		assertNotNull(objectManager);
	}

	@Test
	public void testSetObjects() {
		objectManager.setObjects();
		assertNotNull(gp.objects);
	}

	@Test
	void testQueenBeeObject() {
		objectManager.setObjects();
		assertEquals(23 * gp.tileSize, gp.objects[0].worldX);
		assertEquals(23 * gp.tileSize, gp.objects[0].worldY);
	}

	@Test
	void testTextBubbleObject() {
		objectManager.setObjects();
		assertEquals(1080, gp.objects[1].worldX);
		assertEquals(1056, gp.objects[1].worldY);
	}

}
