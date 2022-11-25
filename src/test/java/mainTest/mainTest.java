package mainTest;

import org.junit.jupiter.api.*;

import main.Main;

public class mainTest {
	Main m;

	@BeforeEach
	public void setUp() {
		m = new Main();
	}

	@Test
	void testMain() {
		Main.main(null);
	}

}
