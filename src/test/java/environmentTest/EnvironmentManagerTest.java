package environmentTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import environment.EnvironmentManager;
import environment.Lighting;
import main.GamePanel;

public class EnvironmentManagerTest {
	private EnvironmentManager environmentManager;

	@BeforeEach
	public void setUp() throws Exception {
		// Define new game panel
		GamePanel gp = new GamePanel();
		environmentManager = new EnvironmentManager(gp);
	}

	@Test
	public void testGetEnvironment() {
		assertNotNull(environmentManager);
	}

	@Test
	public void testGetLighting() {
		environmentManager.setup();
		assertNotNull(environmentManager.getLighting());
	}

	@Test
	public void testSetup() {
		environmentManager.setup();
		assertNotNull(environmentManager);
	}

	@Test
	public void testEnvironmentDraw() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		environmentManager.setup();
		environmentManager.draw(g2);

	}
}
