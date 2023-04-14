package game.portal;

import city.cs.engine.*;
import game.Game;
import game.character.Character;
import game.portal.sensor.PortalSensor;
import org.jbox2d.common.Vec2;

public class Portal extends StaticBody{
    private static final Shape portalShape = new CircleShape(1.5f);
    private static final BodyImage PortalImage = new BodyImage("data/Portal.gif", 3.2f);
    public Portal(World world, float x, float y, Character character, Game game) {
        super(world);
        this.addImage(PortalImage);
        this.setPosition(new Vec2(x,y));

        Fixture fixture = new GhostlyFixture(this, portalShape);
        Sensor sensor = new PortalSensor(this, portalShape, character, game);
    }

}
