package game.portal;

import city.cs.engine.*;
import game.Game;
import character.Character;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class PortalSensor extends Sensor implements SensorListener {
    private final Game game;

    private final Portal portal;

    private static SoundClip teleportSound;
    static {
        try {
            teleportSound = new SoundClip("data/audio/teleport.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public PortalSensor(Portal portal, Shape shape, Game game) {
        super(portal, shape);
        this.game = game;
        this.portal = portal;
        this.addSensorListener(this);
    }
    /**
     * if contact go to next level.
     */
    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character){
            if(game.getfxButton().isSound()) {
                teleportSound.play();
            }
            portal.destroy();
            game.goToNextLevel();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
