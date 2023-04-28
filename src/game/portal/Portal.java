package game.portal;

import city.cs.engine.*;
import game.Game;
import character.Character;
import game.levels.GameLevel;
import game.portal.sensor.PortalSensor;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Portal extends StaticBody{
    private static final Shape portalShape = new CircleShape(1.5f);
    private static final BodyImage PortalImage = new BodyImage("data/Portal.gif", 3.2f);
    private static SoundClip portalSound;

    static {
        try {
            portalSound = new SoundClip("data/audio/portal.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public Portal(GameLevel world, float x, float y) {
        super(world);
        this.addImage(PortalImage);
        this.setPosition(new Vec2(x,y));

        Fixture fixture = new GhostlyFixture(this, portalShape);
        Sensor sensor = new PortalSensor(this, portalShape, world.getCharacter(), world.getGame());
        if(world.getGame().getfxButton().isSound()) {
            portalSound.play();
        }
    }

}
