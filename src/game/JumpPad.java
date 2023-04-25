package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class JumpPad extends StaticBody {
    private static SoundClip jumpPadSound;
    static {
        try {
            jumpPadSound = new SoundClip("data/audio/jumpPad.wav");
            jumpPadSound.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public JumpPad(World w, float xPos, float yPos) {
        super(w, new BoxShape(0.7f, 0.5f));
        this.addImage(new BodyImage("data/jumpPad/jumpPad.png"));
        this.setPosition(new Vec2(xPos, yPos));
    }

    public void playSound(){
        jumpPadSound.play();
    }
}
