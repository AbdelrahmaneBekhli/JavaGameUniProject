package game.collectables.speed.sensor;

import city.cs.engine.Body;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import game.character.Character;
import game.collectables.coin.sensor.CoinSensorListener;
import game.collectables.speed.SpeedBoost;
import game.levels.GameLevel;

public class SpeedSensor extends Sensor {
    public SpeedSensor(SpeedBoost speedBoost, Shape shape, Character character) {
        super(speedBoost, shape);
        SpeedBoostSensorListener sensorListener = new SpeedBoostSensorListener(character, speedBoost);
        this.addSensorListener(sensorListener);
    }
}
