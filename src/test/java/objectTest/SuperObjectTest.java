package objectTest;

import static org.junit.jupiter.api.Assertions.*;
import object.SuperObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import main.GamePanel;


public class SuperObjectTest {

	SuperObject so;
	GamePanel gp;

	@BeforeEach
	public void setUp() throws Exception {
		so = new SuperObject();
		gp = new GamePanel();

	}

	@Test
	void testSuperObject() {
		assertNotNull(so);
	}

	@Test
	void testDrawMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		so.draw(g2, gp);

		so.worldX = -1000;
		gp.bee.worldX = 2;
		so.draw(g2, gp);

		assertNotNull(so);

		so.worldX = 1000;
		gp.bee.worldX = 2;
		so.draw(g2, gp);

		assertNotNull(so);

		so.worldY = -1000;
		gp.bee.worldY = 2;
		so.draw(g2, gp);

		assertNotNull(so);

		so.worldY = 1000;
		gp.bee.worldY = 2;
		so.draw(g2, gp);

		assertNotNull(so);
	}

}
