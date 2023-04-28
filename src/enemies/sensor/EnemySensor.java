package enemies.sensor;

import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import game.Game;
import enemies.Enemy;
import character.Character;
import game.levels.GameLevel;

public class EnemySensor extends Sensor {
    public EnemySensor(Enemy enemy, Shape shape, GameLevel world) {
        super(enemy, shape);
        EnemySensorListener sensorListener = new EnemySensorListener(enemy, world);
        this.addSensorListener(sensorListener);
    }
}
