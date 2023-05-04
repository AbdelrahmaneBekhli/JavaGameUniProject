package game;

import city.cs.engine.*;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
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
    private final GameLevel level;
    public JumpPad(GameLevel level, float xPos, float yPos) {
        super(level, new BoxShape(0.7f, 0.5f));
        this.level = level;
        this.addImage(new BodyImage("data/jumpPad/jumpPad.png"));
        this.setPosition(new Vec2(xPos, yPos));
    }
    /**
     * play jump pad sound
     */
    public void playSound(){
        if(level.getGame().getfxButton().isSound()) {
            jumpPadSound.play();
        }
    }
}
