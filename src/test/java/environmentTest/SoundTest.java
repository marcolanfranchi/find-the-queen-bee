package environmentTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import environment.Sound;

public class SoundTest {

    private Sound sound;

    @BeforeEach
    void setup() {
        sound = new Sound();
    }

    @Test
    void testSoundConstructor() {
        for (int i = 0; i < sound.soundURL.length; i++) {
            URL soundResult = sound.soundURL[i];
            assertNotNull(soundResult);
        }
    }    
}
