package objectTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import object.OBJ_TextBubble;

public class OBJ_TextBubbleTest {

    private OBJ_TextBubble textBubble;

    @BeforeEach
    void setup() {
        textBubble = new OBJ_TextBubble();
    }

    @Test
    void testOBJ_TextBubbleConstructor() {
        String nameResult = textBubble.name;
        assertEquals("Text Bubble", nameResult);
        BufferedImage imgResult = textBubble.image; 
        assertNotNull(imgResult);
        int widthResult = textBubble.width;
        int heightResult = textBubble.height;
        assertEquals(96, widthResult);
        assertEquals(64, heightResult);
    }

    @Test
    void testSetAltImage() {
        BufferedImage originalImage = textBubble.image;

        textBubble.setAltImage();

        BufferedImage imgResult = textBubble.image;
        // Assert new image is not equal to the original image, and that it is not null
        assertNotEquals(originalImage, imgResult);
        assertNotNull(imgResult);
    }
}
