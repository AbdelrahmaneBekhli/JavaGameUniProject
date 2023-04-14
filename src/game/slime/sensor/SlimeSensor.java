package game.slime.sensor;

import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import game.Game;
import game.slime.Slime;
import game.character.Character;
import game.levels.GameLevel;

public class SlimeSensor extends Sensor {
    public SlimeSensor(Slime slime, Shape shape, Character character, GameLevel world, Game game) {
        super(slime, shape);
        SlimeSensorListener sensorListener = new SlimeSensorListener(character, slime, world, game);
        this.addSensorListener(sensorListener);
    }
}
