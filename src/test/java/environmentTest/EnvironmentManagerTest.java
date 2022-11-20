package environmentTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import environment.EnvironmentManager;
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
		assertNotNull(environmentManager.getLighting());
	}

	@Test
	public void testSetup() {
		environmentManager.setup();
		assertNotNull(environmentManager);
	}
}
