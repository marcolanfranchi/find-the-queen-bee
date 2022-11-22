package objectTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import object.OBJ_HoneyDrop;

public class OBJ_HoneyDropTest {

    private OBJ_HoneyDrop honeyDrop;

    @BeforeEach
    void setup() {
        honeyDrop = new OBJ_HoneyDrop();
    }

    @Test
    void testOBJ_HoneyDropConstructor() {
        String nameResult = honeyDrop.name;
        assertEquals("Honey", nameResult);
        BufferedImage imgResult = honeyDrop.image; 
        assertNotNull(imgResult);
        int widthResult = honeyDrop.width;
        int heightResult = honeyDrop.height;
        assertEquals(48, widthResult);
        assertEquals(48, heightResult);
    }
}
