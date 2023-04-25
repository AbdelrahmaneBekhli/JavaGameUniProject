package game.portal.sensor;

import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import game.Game;
import character.Character;
import game.portal.Portal;

public class PortalSensor extends Sensor {
    public PortalSensor(Portal portal, Shape shape, Character character, Game game) {
        super(portal, shape);
        PortalSensorListener sensorListener = new PortalSensorListener(character, portal, game);
        this.addSensorListener(sensorListener);
    }
}
