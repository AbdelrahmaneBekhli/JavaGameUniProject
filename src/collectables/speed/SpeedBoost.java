package collectables.speed;

import city.cs.engine.*;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class SpeedBoost extends DynamicBody {
    private static SoundClip speedBoostSound;

    static {
        try {
            speedBoostSound = new SoundClip("data/audio/speedBoost.wav");
            speedBoostSound.setVolume(0.05);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    private final GameLevel level;
    public SpeedBoost(GameLevel level, float x, float y) {
        super(level);
        this.level = level;
        Shape speedBoostShape = new BoxShape(1,1);
        BodyImage speedBoostImage = new BodyImage("data/collectables/speed boost/speed.gif", 3.5f);
        Fixture fixture = new GhostlyFixture(this, speedBoostShape);
        Sensor sensor = new SpeedSensor(this, speedBoostShape, level.getCharacter());

        this.addImage(speedBoostImage);
        this.setGravityScale(0);
        this.setPosition(new Vec2(x, y));
    }

    @Override
    public void destroy(){
        if(level.getGame().getfxButton().isSound()) {
            speedBoostSound.play();
        }
        super.destroy();
    }
}
