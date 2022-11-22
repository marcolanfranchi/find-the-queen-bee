package objectTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import object.OBJ_QueenBee;

public class OBJ_QueenBeeTest {

    private OBJ_QueenBee queenBee;

    @BeforeEach
    void setup() {
        queenBee = new OBJ_QueenBee();
    }

    @Test
    void testOBJ_QueenBeeConstructor() {
        String nameResult = queenBee.name;
        assertEquals("Queen Bee", nameResult);
        BufferedImage imgResult = queenBee.image; 
        assertNotNull(imgResult);
        int widthResult = queenBee.width;
        int heightResult = queenBee.height;
        assertEquals(48, widthResult);
        assertEquals(48, heightResult);
    }

    
}
