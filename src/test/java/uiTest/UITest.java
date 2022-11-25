package uiTest;

import org.junit.jupiter.api.*;
import static org.junit.Assert.*;

import main.GamePanel;
import main.UI;

public class UITest {

	UI ui;
	GamePanel gp;

	@BeforeEach
	public void setUp() throws Exception {
		gp = new GamePanel();
		ui = new UI(gp);
	}

}
