package collectables.speed.sensor;

import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import character.Character;
import collectables.speed.SpeedBoost;

public class SpeedSensor extends Sensor {
    public SpeedSensor(SpeedBoost speedBoost, Shape shape, Character character) {
        super(speedBoost, shape);
        SpeedBoostSensorListener sensorListener = new SpeedBoostSensorListener(character, speedBoost);
        this.addSensorListener(sensorListener);
    }
}
