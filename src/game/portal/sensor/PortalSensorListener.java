package game.portal.sensor;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.SoundClip;
import game.Game;
import character.Character;
import game.portal.Portal;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class PortalSensorListener implements SensorListener {
    private final Character character;
    private Game game;

    private Portal portal;

    private boolean contact = false;

    private static SoundClip teleportSound;
    static {
        try {
            teleportSound = new SoundClip("data/audio/teleport.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public PortalSensorListener(Character character, Portal portal, Game game) {
        this.character = character;
        this.game = game;
        this.portal = portal;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character){
            teleportSound.play();
            portal.destroy();
            game.goToNextLevel();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}

