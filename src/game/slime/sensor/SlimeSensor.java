package game.slime.sensor;

import city.cs.engine.Body;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import game.slime.Slime;
import game.character.Character;

public class SlimeSensor extends Sensor {
    public SlimeSensor(Slime slime, Shape shape, Character character) {
        super(slime, shape);
        SlimeSensorListener sensorListener = new SlimeSensorListener(character, slime);
        this.addSensorListener(sensorListener);
    }
}
