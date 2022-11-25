package uiTest;

import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import java.awt.Graphics2D;
import java.awt.Graphics;

import main.GamePanel;
import main.UI;

public class UITest {

	UI ui;
	GamePanel gp;
	Graphics g;

	@BeforeEach
	public void setUp() throws Exception {
		gp = new GamePanel();
		ui = new UI(gp);
	}

	@Test
	void testDrawMethod() {
		// gp.paintComponent(g);
	}
}
