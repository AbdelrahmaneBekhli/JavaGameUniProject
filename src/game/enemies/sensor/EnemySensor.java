package game.enemies.sensor;

import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import game.Game;
import game.enemies.Enemy;
import game.character.Character;
import game.levels.GameLevel;

public class EnemySensor extends Sensor {
    public EnemySensor(Enemy enemy, Shape shape, Character character, GameLevel world, Game game) {
        super(enemy, shape);
        EnemySensorListener sensorListener = new EnemySensorListener(character, enemy, world, game);
        this.addSensorListener(sensorListener);
    }
}
